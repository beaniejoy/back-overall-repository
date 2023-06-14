# Transaction 관련 기록

## :pushpin: Nested Method Call (propagation 별 상황)

### 1. `REQUIRED`(default)

> `@Transactional` 기본 propagation: `REQUIRED`  
> 기존 tx가 있으면 사용하고 없으면 새로운 tx 생성해서 사용

```
1. @Transactional parentMethod() "save1" 
2. @Transactional childMethod() "save2"
```
parentMethod(), childMethod() 둘 중 하나에 `throw new RuntimeException(...)`으로  
예외 발생시키면 전체 롤백(예외 발생 시점은 어디든 상관없이 롤백)  

- 부모 메소드에서 자식 메소드 호출하는 부분을 try catch하는 경우
```
1. @Transactional parentMethod() "save1"
    > **childMethod를 try catch로 감싸는 경우**
2. @Transactional(propagation = Propagation.REQUIRED) childMethod() "save2"
    > throw RuntimeException
```
```
Transaction silently rolled back because it has been marked as rollback-only
org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only
```
분명 부모 메소드에서 try catch로 예외를 처리했으나 자식 메소드에서 에러가 발생한 상황  
같은 하나의 Tx 안에서 RuntimeException이 발생하면 이를 rollback 마크를 하게 된다.  
그래서 부모메소드에서 catch 예외 처리해도 전체 Tx는 롤백이 되면서 Exception 발생하게 된다.

- 부모, 자식 둘 다 try catch로 잡는 경우
```
1. @Transactional parentMethod() "save1"
    > **childMethod를 try catch로 감싸는 경우**
2. @Transactional(propagation = Propagation.REQUIRED) childMethod() "save2"
    > throw RuntimeException ... try catch 예외 처리
```
이 때는 둘 다 예외를 처리하였기 때문에 모두 커밋이 되면서 정상 처리가 된다.

<br>

### 2. `REQUIRES_NEW`

- 자식 메소드에서 예외 throwing만 하는 경우
```
1. @Transactional parentMethod() "save1"
2. @Transactional(propagation = Propagation.REQUIRES_NEW) childMethod() "save2"
    > throw RuntimeException
```

nested 메소드에 `@Transactional` propagation을 `REQUIRES_NEW`로 설정해도 부모 메소드도 rollback  
(1, 2 모두 rollback)  
자식 메소드에서 발생한 예외가 부모 메소드로 전파가 되기 때문

- 부모 메소드에서 자식 메소드 호출하는 부분을 try catch하는 경우 (`REQUIRES_NEW` 경우)
```
1. @Transactional parentMethod() "save1"
    > **childMethod를 try catch로 감싸는 경우**
2. @Transactional(propagation = Propagation.REQUIRES_NEW) childMethod() "save2"
    > throw RuntimeException
```
이 경우에는 자식 메소드만 rollback, 부모 메소드는 commit(`REQUIRES_NEW`인 경우에 해당)

- 부모 메소드에서만 예외 발생
```
1. @Transactional parentMethod() "save1"
    > throw RuntimeException
2. @Transactional(propagation = Propagation.REQUIRES_NEW) childMethod() "save2"
    > 정상 동작
```
이 때는 자식 메소드는 commit 되고, 부모 메소드만 rollback이 된다.

<br>

## :pushpin: checked vs unchecked Exception

위의 모든 내용은 RuntimeException 인 경우에 해당(**unchecked exception**)  
(`Error`도 모두 롤백되긴 한다.)  
**checked exception**에 대해서는 rollback이 이루어지지 않는다.  

<br>

### 1. `REQUIRED`(default)

부모 메소드 혹은 자식 메소드에서 `IOException` 발생해도 모두 커밋된다.

<br>

### 2. `REQUIRES_NEW`

- 자식 메소드에서 `IOException` 발생시
```
1. @Transactional parentMethod() "save1"
    > **childMethod를 try catch로 감싸는 경우**
2. @Transactional(propagation = Propagation.REQUIRES_NEW) childMethod() "save2"
    > throw IOException
```
자식 메소드에서만 롤백될 것으로 기대했으나 모두 커밋됨  
`IOException` > checked exception 이기에 롤백 마크대상이 아니다.

- 부모 메소드에서 `IOException` 발생시
```
1. @Transactional parentMethod() "save1"
    > throw IOException
2. @Transactional(propagation = Propagation.REQUIRES_NEW) childMethod() "save2"
    > 정상 동작
```
부모 메소드만 롤백될 것으로 기대했으나 마찬가지로 모두 커밋됨

<br>

## :pushpin: AOP내의 예외 발생 케이스

### 1. Custom Aspect에서 예외 발생시

```
1. - @Transactional parentMethod() "save1"
        > **childMethod를 try catch로 감싸는 경우**
2. - @Transactional(propagation = Propagation.REQUIRES_NEW), 
   - @TestAnnotation 
        childMethod() "save2"
        > 서비스 코드 정상동작 > TestAspect throw RuntimeException
```
Custom Aspect 내에서 RuntimeException 발생,  
parentMethod에서는 childMethod 호출 부분 try catch로 처리  
이 때는 parentMethod > commit / childMethod > rollback  
(try catch로 묶지 않으면 둘 다 rollback 되는 것 참고)

### 2. Aspect에 최우선 순위로 설정한 경우
```java
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TestAspect {
    //...
}
```
Custom Aspect를 최우선 순위로 놓고 1번 상황과 같게 해서 테스트  
parent, child method 둘 다 commit 되었음  
```
@TestAnnotation > @Transactional > Service > @Transactional > @TestAnnotation(여기서 에러)
```
위의 흐름대로 동작해서 이미 transaction 처리가 완료된 이후로 TestAspect에서 오류 발생되어 childMethod rollback X  

### 3. Aspect에 throw checked exception 
aop order는 원래대로 설정
```
1. - @Transactional parentMethod() "save1"
        > **childMethod를 try catch로 감싸는 경우**
2. - @Transactional(propagation = Propagation.REQUIRES_NEW), 
   - @TestAnnotation 
        childMethod() "save2"
        > 서비스 코드 정상동작 > TestAspect throw IOException
```
원래 `IOException`은 transaction의 롤백 마크 대상이 아니지만  
childMethod는 rollback이 된다.
AOP상에서 발생하는 checked exception은 `UndeclaredThrowableException`로 wrapping 되어 변환되는데  
해당 예외는 `RuntimeException`이다. (https://www.baeldung.com/java-undeclaredthrowableexception)


### 4. 특이한 케이스 (with propagation `REQUIRED`)
```
1. - @Transactional parentMethod() "save1"
        > ** childMethod를 try catch X **
2. - @Transactional(propagation = Propagation.REQUIRED), 
   - @TestAnnotation 
        childMethod() "save2"
        > 서비스 코드 정상동작 > TestAspect throw RuntimeException
```
1. childMethod를 기본 propagation 전략으로 설정   
2. parentMethod에서는 childMethod 호출 부분을 try catch로 처리하지 않음
3. TestAspect AOP의 순서를 최우선순위로 설정(tx보다 앞서게)
4. TestAspect에서 throw `RuntimeException`

위의 상황에서는 parentMethod에서 진행한 save 내용은 rollback되고  
childMethod에서 진행한 save 내용은 commit 됨


> **정리**  
> - AOP에서 에러 발생시 RuntimeException으로 wrapping 되어 throw
> - 만약 custom AOP가 spring tx안에 있다면 롤백이 된다.(RuntimeException 이기에)
> - custom AOP 우선순위가 spring tx AOP보다 앞선다면 tx안에 묶이지 않아 롤백 X
> - 호출하는 쪽(parentMethod)에서는 childMethod에 custom AOP가 에러가 발생해도 childMethod에서 발생한 것이라 판단해서 롤백 여부 판단
