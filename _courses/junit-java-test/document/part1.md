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

## :pushpin: JUnit5 Assertion

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

## :pushpin: 조건에 따라 테스트 실행하기

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

## :pushpin: 태깅과 필터링

```java
@Test
@DisplayName("스터디 만들기 fast")
@Tag("fast")
```

```groovy
tasks.named('test') {
    useJUnitPlatform {
        includeTags 'fast'
        excludeTags 'slow'
    }
}
```

- `./gradlew --info clean test`

```groovy
task ciTest(type: Test) {
    useJUnitPlatform {
        includeTags 'fast | slow'
    }
}
```

- 이런 식으로 별도 gradle 작업을 만들어서 특정 tag만 실행하도록 설정할 수 있다.
- **profile 별로 설정하는 것은 알아봐야 할 듯**

<br>

## :pushpin: 커스텀 태그

```java

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test
@Tag("fast")
public @interface FastTest {
}
```

- 기존 `@Tag("fast")`는 type safe 하지 않다. (fast 값에서 오타 발생 가능)
- 커스텀 태그를 통해 미리 지정해놓고 어노테이션으로 사용 가능

<br>

## :pushpin: 반복 테스트

- `RepeatedTest`
- `ParameterizedTest` (JUnit5 기본 제공)

```java
@DisplayName("스터디 만들기 RepeatedTest")
@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
void repeatTest(RepetitionInfo repetitionInfo){
        // 현재 몇번째 반복 진행중인지 / 전체 반복 횟수
        System.out.println("test "+repetitionInfo.getCurrentRepetition()+"/"+repetitionInfo.getTotalRepetitions());
        }

// JUnit5 기본 제공
@DisplayName("스터디 만들기 ParameterizedTest")
@ParameterizedTest(name = "{index} {displayName} message={0}")
@ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
void parameterizedTest(String message){
        System.out.println(message);
        }
```

<br>

## :pushpin: 테스트 인스턴스

```java
@FastTest
@DisplayName("스터디 만들기 fast")
void create_new_study_fast(){
        System.out.println(this);   // 테스트 마다 인스턴스가 다르다.
        System.out.println("create fast");
        }

@SlowTest
@DisplayName("스터디 만들기 slow")
void create_new_study_slow(){
        System.out.println(this);   // 테스트 마다 인스턴스가 다르다.
        System.out.println("create slow");
        }
```

- JUnit은 기본적으로 테스트 메소드마다 인스턴스를 생성해서 사용
- 테스트간에 의존성을 없애기 위해(공유하는 값 제거)

```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
```

- 이렇게 하면 테스트가 클래스 단위로 수행하게 된다. (기존 메소드 단위)
- `@BeforeAll`, `@AfterAll` 같은 경우 static일 필요가 없어짐

<br>

## :pushpin: JUnit5 확장 모델

- https://junit.org/junit5/docs/current/user-guide/#extensions

```java
@ExtendWith(FindSlowExtension.class) // 1.. 첫 번째 방법
class StudyExtensionTest {
    // 2... 두 번째 방법
    @RegisterExtension
    static FindSlowExtension findSlowExtension = new FindSlowExtension(1000L);
}
```
```properties
# junit extension auto detection
junit.jupiter.extensions.autodetection.enabled=true
```
- ServiceLoader를 통한 Extension 클래스 자동 등록(지양)
  - 하지만 명시적으로 Extension 클래스를 등록하는 것이 좋다.