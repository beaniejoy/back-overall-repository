# Section 2. 스프링 시큐리티 주요 아키텍처 이해

## 📌 위임 필터 및 필터 빈 초기화
> - DelegatingProxyChain
> - FilterChainProxy

### DelegatingProxyChain
- 원래 Servlet Filter 만으로 Spring bean의 Injection 기능을 사용 못함(순수 서블릿 영역)
- Spring Bean으로 관리되는 Filter를 생성
- `DelegatingFilterProxy`를 둬서 Servlet Filter로 들어오는 요청을 위임하고 Spring Bean(`FilterChainProxy`)으로 건네준다.

```text
Servlet Filter -> DelegatingFilterProxy(요청 위임) -> Spring Bean(Filter)
```
- `DelegatingFilterProxy`는 `springSecurityFilterChain` 이름으로 된 스프링 빈을 찾게 된다.

### FilterChainProxy
![Picture1](https://user-images.githubusercontent.com/41675375/174445881-f8ad2af2-3de1-419c-850a-7d33797c28fc.png)

- `springSecurityFilterChain` 이름으로 생성되는 Filter bean(스프링 빈으로 생성)
- `DelegatingFilterProxy`로 부터 요청 위임받고 실제 보안 처리
- 사진의 필터들을 관리하고 제어
- 요청 때마다 필터 순서대로 호출
  - 스프링 시큐리티가 기본적으로 생성하는 필터
  - 설정 클래스에서 API 추가 시 생성되는 필터
    - `formLogin()` api 추가시 아래 필터 생성
    - `UsernamePasswordAuthenticationFilter`
    - `DefaultLoginPageGeneratingFilter`
    - `DefaultLogoutPageGeneratingFilter`
  - 사용자 정의 필터를 생성해서 기존 필터들의 전, 후로 추가 가능
  - 마지막 까지 인증, 인가 예외가 발생하지 않으면 보안 통과
- `SecurityFilterAutoConfiguration`
  - 여기서 스프링 시큐리티 관련 기본 설정을 해준다
  - `DelegatingFilterProxyRegistrationBean`: `springSecurityFilterChain` 이름으로 `DelegatingFilterProxy` 등록하고 있음

<br>

## 📌 필터 초기화와 다중 보안 설정

- 설정 클래스 별로 보안 기능이 각각 작동
- 설정 클래스 별로 `RequestMatcher` 설정 (`http.antMatcher("/admin/**")`)
- `FilterChainProxy`가 각 설정 클래스의 필터들을 가지고 있음
  - `SecurityFilterChains`에서 관리
- 요청에 따라 `RequestMatcher`와 매칭되는 필터가 작동하도록 함
```java
// FilterChainProxy doFilterInternal
List<Filter> filters = getFilters(firewallRequest);
```
- 여기서 SecurityFilterChains 에 담겨진 설정 내용들(필터 정보들) 중 request url과 matching하는 내용을 꺼내서 사용

### 다중 보안 설정시 `@Order`의 중요성
- 설정 파일들의 순서를 설정해야 되는데 url의 범위가 더 큰 것을 우선순위에 두면 문제가 발생
- spring security는 순서대로 url matching 작업을 하는데 큰 범위의 url에 걸러져서 원치않는 설정파일의 필터들이 사용될 수 있음

<br>

## 📌 인증 개념 이해 - Authentication

- 인증 정보를 저장하는 토큰 개념
- id, password 정보는 검증을 위해 전달되어 사용됨
- 인증 후 인증 결과(user 객체, 권한정보)를 담고 `SecurityContext`에 저장되어 전역적으로 사용
- 구조
  - principal: 사용자 id, User 객체 저장
  - credentials: 비밀번호
  - authorities: 인증된 사용자의 권한 목록
  - details: 인증 부가 정보
  - Authenticated: 인증 여부

### 과정
- 사용자가 username(id) + password 입력
- `UsernamePasswordAuthenticationFilter` 여기서 인증 과정 진행
- `Authentication` 객체 생성
  - `principal`: username
  - `credentials`: password
  - `authorities`: ---
  - `authenticated`: false
- `AuthenticationManager` 에서 인증 과정 위임 및 진행
- 인증 후 `Authentication` 객체 반환
  - `principal`: UserDetails
  - `credentials`: ---
  - `authorities`: ROLE_ADMIN, ...
  - `authenticated`: true
- 최종 인증 결과를 저장(ThreadLocal 공간)
  - `SecurityContextHolder` - `SecurityContext` > `Authentication`

<br>

## 📌 인증 저장소
> - SecurityContextHolder
> - SecurityContext

### SecurityContext
- `SecurityContext` > `Authentication` > User 정보
- `ThreadLocal`에 저장, 전역적으로 참조 가능
  - 쓰레드마다 할당된 고유 저장소가 존재(thread-safe)
- 인증이 완료되면 `SecurityContext`는 `HttpSession`에 저장
  - 어플리케이션 전반에 걸쳐 전역적으로 참조 가능

### SecurityContextHolder
- `SecurityContext` 객체 저장 방식 결정
  - `MODE_THREADLOCAL`: 기본값, 쓰레드당 SC 할당
  - `MODE_INHERITABLETHREADLOCAL`: 메인 스레드와 자식 스레드에 대해 동일 SC 유지
  - `MODE_GLOBAL`: 애플리케이션에서 단 하나의 SC 저장
- `clearContext()`: SC 초기화
```java
Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
```
- 이런 식으로 꺼내서 사용
- 인증된 사용자가 다시 api 접근할 때는 `session`에 저장된 SC를 가지고 다시 `ThreadLocal`에 저장

### 관련 클래스
- `ThreadLocalSecurityContextHolderStrategy`: ThreadLocal 선언
- `SecurityContextImpl`: SC 구현체

<br>

## 📌 인증 저장소 필터 - SecurityContextPersistenceFilter
- Session 저장공간과 `SecurityContextHolder` 저장공간이 따로 있다는 것을 인지
- Session은 세션 방식으로, SC Holder는 `ThreadLocal`(default) 방식으로 각각 SC 저장
- `SecurityContextPersistenceFilter` 내부에는 `SecurityContextRepository`를 가지고 있음
  - **여기서 SC를 생성하고 조회하는 역할 수행**

### 익명사용자
- 처음에 인증전에 api 호출하면 시큐리티 필터에서는 익명사용자로 인식
- Session에는  SC 객체