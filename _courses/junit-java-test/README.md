# JUnit5 Java Test

- [더 자바, 애플리케이션을 테스트하는 다양한 방법(inflearn)](https://www.inflearn.com/course/the-java-application-test)

## Index
- [part 1. JUnit5](https://github.com/beaniejoy/back-overall-repository/blob/main/_courses/junit-java-test/document/part1_junit5.md)
- [part 2. Mockito](https://github.com/beaniejoy/back-overall-repository/blob/main/_courses/junit-java-test/document/part2_mockito.md)

<br>

## Setting
```
Execution failed for task ':bootJar'.
> Error while evaluating property 'mainClass' of task ':bootJar'
```
- gradle build 시 위와 같은 에러 발생
- spring boot run application 클래스가 존재하지 않아서 발생
```groovy
bootJar {
enabled = false
}
```