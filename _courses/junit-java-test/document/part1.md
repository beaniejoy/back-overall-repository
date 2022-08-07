# Part 1. JUnit 5

## :pushpin: JUnit5 시작하기

- spring boot 2.2 버전부터 **JUnit5**가 기본 설정
    - `spring-boot-starter-test` 기본 설정이 JUnit5버전
- JUnit5 부터 class, method가 public 필요가 없어짐
    - 5버전부터 java reflection 사용해서 접근가능

### Basic JUnit5 Annotation

- `@Test`
- `@BeforeAll` / `@AfterAll`
- `@BeforeEach` / `@AfterEach`
- `@Disabled`

<br>

## :pushpin: 테스트 이름 표시하기

### `@DisplayNameGeneration`

```java
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
```

- class, method 단위에서 둘 다 사용 가능

### `@DisplayName`

<br>

## JUnit5 Assertion

```java
assertEquals(StudyStatus.DRAFT,study.getStatus(),"스터디 최초 상태는 DRAFT여야 합니다.");
```

- 맨 마지막 인자에 테스트 실패시 나오는 메시지를 지정할 수 있다.
- 만약 메시지 자체에 연산이 필요하고 그 연산이 좀 복잡하다면 Supplier를 이용해 작성하는 것이 좋다.
    - 람다 표현식을 통해 메시지를 지정하면 테스트 실패 시에만 해당 메시지를 연산하게 된다.

### assertAll
```java
assertAll(
        ()->assertNotNull(study),
        ()->assertEquals(StudyStatus.DRAFT,study.getStatus(),"스터디 최초 상태는 DRAFT여야 합니다."),
        ()->assertTrue(study.getLimit()>0,"스터디 최대 참석 가능 인원은 0보다 커야 합니다.")
);
```
- 원래 두 번째 assert에서 실패하면 거기서 테스트가 끝나게 된다.
- `assertAll`을 사용하면 전체 assert 중에 어떤 것들이 실패했는지 한꺼번에 보여준다.

### timeout
- `assertTimeout`
  - 람다로 지정한 식을 다 실행하고나서 목표로하는 시간과 비교
- `assertTimeoutPreemptively`
  - 목표로하는 시간까지만 측정, 그 이후는 실패처리
  - 람다안의 식을 별도 쓰레드(`ThreadLocal`)로 실행하기 때문에 Transactional 같은 내용을 테스트할 때 **주의**  
    (Transactional이 걸린 쓰레드와 다른 별도의 쓰레드에서 처리가 될 수 있어서 예상치 못한 결과가 나올 수 있다.)

### 기타
- `AssertJ`, `Hemcrest`, `Truth` 등의 라이브러리로도 테스트 코드를 작성할 수 있다.

<br>

## 조건에 따라 테스트 실행하기
- `assumeTrue([조건])`
  - 조건에 만족하면 그 아래 테스트를 실행
  - 조건 만족하지 않으면 Disabled 된 것처럼 실행 X
- `assumingThat([조건], () -> { 실행구문 })`
  - 조건에 해당하면 실행구문안에 내용을 실행
- `@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")`
  - 이런식으로 어노테이션으로 구분 가능
- `@EnabledOnOs()`: 어노테이션으로 OS 조건을 가지고 실행 여부 설정 가능
- `@EnabledOnJre()`: 특정 자바 버전을 가지고 조건 설정

<br>

## 태깅과 필터링
- `@Tag("fast")`
```groovy
tasks.named('test') {
    useJUnitPlatform {
        includeTags 'fast'
        excludeTags 'slow'
    }
}
```
- `./gradlew --info test`