# Flyway DB migration docker compose With dockerize

- 기존 docker-dbmigration-flyway에서 에러 발생 가능성 고려
- `connectRetries`로 mysql 서버 구동완료 이후 migration 진행은 가능
- migration과 seed 쿼리 실행 시점이 순차적이지가 않다는 점에서 문제 발생
- docker compose 설정 내에서 `depends_on`은 `command`의 순서까지 보장해주진 않는다.
  - [관련 Link](https://jupiny.com/2016/11/13/conrtrol-container-startup-order-in-compose/)

## Directory Structure
```
project
├── env
    └── mysql.env (mysql 도커 환경변수)
├── flyway 
    ├── conf
    	└── flyway_main.conf (migration 관련 flyway 설정내용)
    └── db-migration
    	├── V001_Create_table_name.sql (migration 대상 sql 파일들)
    	└── ...
    ├── docker-entrypoint.sh (dockerize를 통한 mysql, migration 순차 실행 shell script)
    └── Dockerfile (flyway, dockerize docker 설정 내용)
└── docker-compose.yml (docker compose 설정)
```

## Run Docker Compose

```bash
$ docker-compose up --build
```

## dockerize setting

```docker
...
USER root
# run flyway in order through dockerize utility.
RUN apk add --no-cache openssl

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

...

RUN chmod +x docker-entrypoint.sh

...
```
- `/flyway/Dockerfile` 설정 내용
- `USER root`: apk permission을 위한 root 계정 접근
- `RUN chmod +x docker-entrypoint.sh`: script 실행접근권한 설정

## Entrypoint Shell Script

```bash
#!/bin/bash
echo "wait DB container up"
dockerize -wait tcp://db-mysql:3306 -timeout 20s

# DB Migration
echo "run database migration"
flyway -configFiles=/flyway/conf/flyway_main.conf -locations=filesystem:/flyway/sql/main migrate

# Seed Migration
echo "insert seed data"
flyway -configFiles=/flyway/conf/flyway_seed.conf -locations=filesystem:/flyway/sql/seed migrate
```

- `dockerize -wait`: mysql server container가 docker 상에 up이 될 때까지 wait
- 이렇게 되면 mysql 서버가 올라온 후에 migration이 실행되기에 기존에 `-connectRetries=60` 설정은 불필요

## flyway configuration

```conf
flyway.url=jdbc:mysql://db-mysql:3306/test-schema?autoreconnect=true&characterEncoding=utf8&serverTimezone=Asia/Seoul
```
- url에 query string으로 `characterEncoding=utf8` 지정해주어야 insert시 한글 깨짐 방지할 수 있음