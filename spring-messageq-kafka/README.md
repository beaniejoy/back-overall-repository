# Spring Message Queue (Kafka)

## Run Kafka
```shell
$ docker-compose up --build
```

## Setting
```yaml
# application.yml
bootstrap-servers: 127.0.0.1:9092
```
spring boot app 따로 실행시 localhost로 지정

```yaml
# application.yml
bootstrap-servers: kafka:9092
# docker-compose.yml
container_name: kafka
environment:
  KAFKA_ADVERTISED_HOST_NAME: kafka
```
같은 docker-compose.yml 파일 안에 spring boot application 설정을 포함하면 같은 docker network로 묶어줘야 한다.

## Note
### Comsumer Group
- consumer group은 하나의 topic 담당
- 하나의 topic에 여러 consumer group이 붙을 수 있다.