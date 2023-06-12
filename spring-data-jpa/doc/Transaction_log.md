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

## :pushpin: checked vs unchecked

위의 모든 내용은 RuntimeException 인 경우에 해당(**unchecked exception**)  
(`Error`도 모두 롤백되긴 한다.)  
**checked exception**에 대해서는 rollback이 이루어지지 않는다.  

<br>

### 1. `REQUIRED`(default)

부모 메소드 혹은 자식 메소드에서 IOException 발생해도 모두 커밋된다.

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
IOException > checked exception 이기에 롤백 마크대상이 아니다.

- 부모 메소드에서 `IOException` 발생시
```
1. @Transactional parentMethod() "save1"
    > throw IOException
2. @Transactional(propagation = Propagation.REQUIRES_NEW) childMethod() "save2"
    > 정상 동작
```
부모 메소드만 롤백될 것으로 기대했으나 마찬가지로 모두 커밋됨
