# Flyway DB migration docker compose

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
└── docker-compose.yml (docker compose 설정)
```

## Run Docker Compose

```bash
$ docker-compose up --build
```

## Flyway Configuration

- [flyway official page (config file)](https://flywaydb.org/documentation/configuration/configfile)

```conf
# data encoding
flyway.encoding=UTF-8
```