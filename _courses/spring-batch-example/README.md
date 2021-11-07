# Spring Batch Basic

- fastcampus

## Spec
- Java 11
- MySQL
- H2 Database
- Gradle
- Spring Boot 2.5.6
- Spring Batch
- Spring Data JPA

## Setup
```java
@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchExampleApplication {
    //...
}
```

## Job
- Job : spring batch의 실행 단위
- Step : Job의 실행 단위
```java
// HelloConfiguration.java
private final JobBuilderFactory jobBuilderFactory;

public HelloConfiguration(JobBuilderFactory jobBuilderFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
}

@Bean
public Job helloJob() {
    return jobBuilderFactory.get("helloJob")
            .incrementer(new RunIdIncrementer())
            .start()
            .build();
}
```
- `JobBuilderFactory`
  - `get("job_name")`
    - job의 이름
    - spring batch를 실행시켜줄 수 있는 key 
  - `start("step_name")`
    - job 실행시 최초로 수행할 step 지정
    - `StepBuilderFactory`에 의해 생성된 Step 객체를 넣어준다.
- `RunIdIncrementer`
  - job이 생성될 때마다 파라미터 id를 자동으로 생성해주는 클래스

### 특정 Job 실행
- 여러 job을 설정하고 애플리케이션을 그냥 실행하면 모든 배치가 실행이 된다.
- 선택적으로 배치를 실행할 수 있어야하고 평소에는 배치가 실행되지 않도록 하는 것이 좋다.

```shell
--spring.batch.job.names=[job_name]
```
- `[job_name]`을 실행 argument에 지정함으로써 특정 Job만 배치실행 가능

```yaml
spring:
  batch:
    job:
      names: ${job.name:NONE}
```
- 이렇게 해서 `--job.name=[job_name]` argument로 실행하면 특정 job만 실행 가능
- `job.name` argument 없이 실행하면 실행되는 배치가 없도록 설정됨

## Step
- Job의 세부 실행 단위, N개가 등록돼 실행
- Step의 실행 단위
  - Task
    - 하나의 작업 기반
    - 10000개 데이터를 한꺼번에 해도 무리가 없다고 판단할 때 Task로 작업
  - Chunk
    - 하나의 큰 덩어리를 n개씩 나눠서 실행
    - 10000개 데이터 한꺼번에 하기엔 무리 -> 1000개씩 나눠서 실행
  - Task도 Chunk처럼 페이징 처리할 수 있지만 명확히 책임을 나눠서 실행할 수 있다.
    - Chunk 기반 Step : `ItemReader`, `ItemProcessor`, `ItemWriter`
  
## Spring Batch 관련 테이블 구조
- Spring Batch 관련 메타데이터를 담는 테이블 구조를 제공함
- 스프링 배치를 실행하고 관리하기 위한 테이블
- `spring-batch-core/org.springframework/batch/core/*` 여기에 위치
- `schema-xxx.sql` : database에 따라 DDL 쿼리 제공
- yaml 설정파일에 다음과 같이 스키마 실행 설정을 할 수 있다.
```yaml
spring:
  batch:
    jdbc:
      initialize-schema: never
```
- `spring.batch.jdbc.initialize-schema`로 변경
  - `always`(개발)
  - `embedded`(개발/h2 같은 인메모리)
  - `never`(운영 환경일 때 추천, 테이블이 적용안되어있으면 직접 `schema.sql`을 실행해야함)
- default 값은 `embedded`