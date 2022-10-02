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
![Screen Shot 2022-06-29 at 11 07 16 PM](https://user-images.githubusercontent.com/41675375/176457421-f797249d-3a11-4710-a678-47187b5e3425.png)

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
  - `SecurityController`, `SecurityContextHolderConfig` 참고
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

<br>

## 📌 인증 흐름 이해 - Authentication Flow
1. `UsernamePasswordAuthenticationFilter`
   - `Authentication` 객체 생성(id, password 요청 데이터로)
   - 인증 성공시 `Authentication`을 `Manager`로부터 받아서 SC에 저장
2. `AuthenticationManager`
   - `List` 형식으로 `Provider` 객체를 저장, 관리
   - 실제 인증 처리를 적절한 `Provider`를 찾아 해당 `Provider`에게 위임
3. `AuthenticationProvider`
   - 여기서 `User` 유효성 검증(패스워드 체크)
   - 실제 인증 처리 작업
   - 인증 성공 시 `Authentication`(`UserDetails`, `authorities`) 생성 및 반환
4. `UserDetailsService`
   - `loadUserByUsername(username)`
   - Provider 안에서 `findById`를 통해 User 객체 조회
     - 여기서 사실상 ID 체크, 없으면 예외 발생
   - `UserDetails` 타입으로 (`return UserDetails`)
   - 그 이후 `Provider`에서 password 검증
5. `Repository`
   - 유저 객체 조회(`return User`)
   - User 객체 조회 시도 시 실패했을 때 Filter가 예외를 받아서 처리(fail handler 수행)

### AuthenticationProvider 로직
- `AbstractUserDetailsAuthenticationProvider`에서 인증을 위한 실제 로직 진행
- `retrieveUser` 통해 UserDetails를 가져오는 로직이 있는데 여기서 `loadUserByUsername`을 수행
  - `DaoAuthenticationProvider` 여기서 실제 `retrieveUser` 구현 로직 수행

<br>

## 📌 인증 관리자 - AuthenticationManager
- 위의 인증 흐름 이해에서 `UsernamePasswordAuthenticationFilter`에서 `Authentication` 객체를 받아 적절한 `Provider`에게 위임하는 역할 수행
  - `AuthenticationProvider` 목록 중 인증 처리 요건에 맞는 것을 찾아 인증 처리 위임
- 인증시 `ProviderManager`를 사용

### ProviderManager
- Form 인증, RememberMe 인증, Oauth 인증 등 여러 인증이 존재
- `ProviderManager`는 여러 인증 요청을 보고 적절한 `Provider`에게 위임
  - `DaoAuthenticationProvider`
  - `RememberMeAuthenticationProvider`
- Oauth 인증 같은 경우 처리할 수 있는 `ProviderManager` 객체가 없음
  - `parent` 속성 사용 (`parent`: `AuthenticationManager` 타입의 클래스를 저장)
  - `parent`에서 다른 Provider를 찾을 수 있다. (`OauthAuthenticationProvider`)

### 참고해야할 클래스
- `AuthenticationManagerBuilder`
  - `AuthenticationManager` 객체를 생성하는 역할 수행
  - 여기서 `Provider`를 등록

### 과정
- `UsernamePasswordAuthenticationFilter`
  - 요청에서 username, password 추출해 `Authentication`(`UsernamePasswordAuthenticationToken`) 객체 생성
  - `AuthenticationManager::authenticate(Authentication)` 호출
- `AuthenticationManager`
  - `ProviderManager`가 실제 인증 과정 처리
    - 자체적으로 가지고 있는 provider: `AnonymousAuthenticationProvider`
    - **parent** ProviderManager's provider: `DaoAuthenticationProvider`
    - 자체 provider `supports` 조건 체크(Authentication 가지고 조건 체크) -> 아니면 parent의 provider로 넘김

<br>

## 📌 인증 처리자 - AuthenticationProvider
- `AuthenticationProvider`: 인터페이스
- `authenticate(authentication)`, `supports(authentication)` 두 개 메소드를 구현해야 한다.

### authenticate(authentication)
1. ID 검증
   - `UserDetailsService`
   - `loadUserByUsername(username)`를 통해 user(`UserDetails`) 조회 및 ID 검증
   - 없으면 `UserNotFoundException` throw
2. password 검증
   - 위에 `UserDetails` 반환 받은 내용을 토대로 입력 받은 password와 비교
   - 불일치시 `BadCredentialException` throw
3. 추가 검증
   - 추가 검증도 거치게 됨
4. 인증 성공
   - `Authentication` 객체에 User 정보, authorities 담아서 `AuthenticationManager`에게 반환

<br>

## 📌 인가 개념 및 필터 이해 - Authorization, FilterSecurityInterceptor
- 스프링 시큐리티가 지원하는 권한 계층
  - 웹 계층: URL
  - 서비스 계층: 기능 단위(메소드)
  - 도메인 계층

### FilterSecurityInterceptor
- 스프링 시큐리티에서 설정된 필터 중 가장 마지막에 수행
- 인증된 사용자에 대한 특정 요청의 승인/거부(인가) 여부 최종적으로 결정

### 주요 과정
- `FilterSecurityInterceptor`에 진입
- 인증(authentication) 객체 유무 체크
- `SecurityMetadataSource`
  - 요청한 자원에 필요한 권한 정보 조회(ex. `/user` -> `ROLE_USER`)
  - null일 경우 자원 접근 허용
- `AccessDecisionManager` 
  - 심의 결정자(접근 권한 결정)
  - `AccessDecisionVoter`에게 심의 위임(승인/거부 내용 전달)
  - 접근 승인 유무 체크 후 허용할지 `AccessDeniedException` 던질지 결정
