# Spring Email Service
(작성중)
> 1. DB 이벤트와 메시지 발송 간의 데이터 불일치를 방지하고자 하기 위한 OutBox 패턴 적용 연습  
> 2. scale-out 상황에서 MessageRelay 역할을 하는 Scheduler 중복 실행 방지를 위한 ShedLock 적용

- OutBox pattern (using DB)
- Scheduler (ShedLock)
- docker / docker-compose
- mysql (DB)
- nginx (Load Balancer)

## UML Diagram

![outbox_pattern](https://user-images.githubusercontent.com/41675375/125115655-98976e80-e126-11eb-9b84-451c59caa644.png)

## Run Application

### Local Env
```shell
$ ./gradlew clean build --exclude-task test

$ java -jar \
  -Dspring.profiles.active=dev,mail \
  -Dmail.username=<MAIL_ADDRESS> \
  -Dmail.password=<PW or App PW> \
  ./build/libs/*.jar
```

### Docker(추후 적용)
```bash
$ ./gradlew clean build --exclude-task test

$ docker-compose up
```

## Blog
- [https://beaniejoy.tistory.com/48](https://beaniejoy.tistory.com/48)
