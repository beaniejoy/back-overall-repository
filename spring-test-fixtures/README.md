# Manage test dependencies
- Spring Boot multi-module

toss tech 블로그에서 발견한 multi-module 환경에서의 test dependencies 관리 실습  
([link 참고](https://toss.tech/article/how-to-manage-test-dependency-in-gradle))

## 사용하는 이유

Test에서만 사용되는 utils, helper, builder 클래스를 multi-module에서 하나의 공통 모듈안에서 관리  
다른 모듈에서 test 코드 구성할 때 공통 모듈의 클래스들을 import 해야하는 상황 발생  

## 사용방법

- gradle에서 제공하는 `java-test-fixtures` plugin 사용하면 됨

```kotlin
// domain module
plugins {
    `java-test-fixtures`
}
```
외부 모듈로 노출되고자 하는 공통 모듈의 `build.gradle.kts`에 위와 같이 설정
```
- main
- test
- testFixtures
    - kotlin
    - resources
```
위와 같이 `testFixtures` directory 생성가능  
해당 디렉토리 내부에다가 외부로 노출하고자 하는 테스트용 공통 클래스들을 관리하면 됨 
```kotlin
// db, application module
dependencies {
    //...
    testImplementation(testFixtures(project(":domain")))
}
```
공통 모듈의 클래스를 import해서 사용하려는 외부 모듈에서 `build.gradle.kts`에 위와 같이 설정  

## 효과
- 장점
  - 테스트 용도로 외부 모듈에서 필요로 하는 공통 클래스에 대해서만 노출 가능
    - test 디렉토리에 있는 내용들은 외부에 노출 X, @Test 쓰던대로 작성하면됨
  - 외부 모듈에서 test가 아닌 main 디렉토리에서 `testFixtures` 내용은 접근 불가능
    - `testImplementation`으로 가져왔기 때문