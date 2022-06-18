# Section 1. 스프링 시큐리티 기본 API 및 Filter 이해

- `SecurityContextHolder`
  - 인증 저장소
  - `SecurityContext` 관리
    - `Authentication` 객체 보관
- SecurityContext와 session에 인증 객체 각각 저장(??)

<br>

## 📌 사용자 정의 보안 기능 구현
- `WebSecurityConfigurerAdapter`: 스프링 시큐리티 웹 보안 기능 초기화 및 설정
    - `HttpSecurity`: 세부적인 보안 기능 설정할 수 있는 API 제공
    - SecurityConfig 설정 클래스가 adapter를 상속받아서 시큐리티 내용 설정
    - 여기서 초기화 작업 진행 후 Config 파일에 등록한 사용자정의 설정 내용 적용

<br>

## 📌 Form Login 인증
- `SecurityConfig.kt` 참고

<br>

## 📌 Form Login 인증 필터
- `UsernamePasswordAuthenticationFilter`
  - 인증 관련 작업을 진행하는 filter(login form 인증)
  - `AntPathRequestMatcher("/login")`
    - 인증 처리 경로가 맞는지 체크
    - `HttpMethod(POST)`, `loginProcessUrl` 가지고 일치여부 체크 (false면 다음 filter로 넘어감)
  - `Authentication`(인증 전) - username, password
  - `AuthenticationManager` -> `AuthenticationProvider` 위임 (여기서 실질적인 인증 성공 여부 처리 진행)
    - 인증 실패시 `AuthenticationException` throw
    - 성공시 `AuthenticationManager`에게 인증성공 응답
  - `Authentication`(인증 성공 후) - User 객체, Authorities (권한 정보)
  - `SecurityContext`에 저장 -> session에도 저장
  - `SuccessHandler`: 후속 처리
- `FilterChainProxy`
  - filter들을 관리하는 bean
  - Security를 등록하면 관련 filter들도 등록된다.

<br>

## 📌 Logout 처리, LogoutFilter
- `SecurityConfig.kt` 참고
- 세션 무효화, 인증토큰 삭제(Authentication 객체, SecurityContext), 쿠키정보 삭제, 로그인 페이지로 redirect

<br>

## 📌 Remember Me 인증
- 세션이 만료되고 웹 브라우저가 종료된 후에도 애플리케이션이 사용자를 기억하는 기능
- 쿠키를 사용
- 원래는 로그인을 통해 인증을 하면 `JSESSIONID`를 통해 이후 요청에 대해서 인증을 처리해준다.
- remember-me 활성화시 `JSESSIONID`, `remember-me` cookie도 생성
  - 이 때는 `JESSIONID`를 지워도 `remember-me` cookie 내용을 가지고 다시 인증 처리

<br>

## 📌 Remember Me Filter
- `RememberMeAuthenticationFilter`
- `SecurityContext` 안에 `Authentication` null인 경우 해당 필터가 동작
  - session timeout 만료된 경우
  - 브라우저 종료로 session이 끊긴 경우
  - 위 경우에 인증을 유지된 상태로 이용가능하게 해준다.
- `RememberMeServices` - 저장된 토큰과 클라이언트에서 보낸 토큰과 비교
> Question  
> session timeout 만료된 경우에는 어떻게 remember-me cookie를 활용하는 건지?

<br>

## 📌 AnonymousAuthenticationFilter
- 익명사용자 인증처리
- 기존에는 session에 있는 user 객체가 null이면 인증받지 않은 사용자로 분류
- null인 경우 익명사용자로 분류
  - `SecurityContext`에 anonymousUser로 저장
- 인증객체를 세션에 저장하진 않는다.
- `AbstractSecurityInterceptor` - 여기서 최종 인가 처리를 해준다.
```kotlin
http
  .authorizeRequests()
  .anyRequest().authenticated()
```
이 상태여야 한다.

<br>

## 📌 동시 세션 제어, 세션 고정보호, 세션 정책

### 동시 세션 제어
- 최대 세션 허용 개수 초과 -> 해결방법 2가지 존재
  - 이전 사용자 세션 만료
  - 현재 사용자 인증 실패
```kotlin
http
  .sessionManagement() // 세션 관리 기능 동
  .invalidSessionUrl("/invalid") // 세션이 유효하지 않을 때 이동할 페이지
  .maximumSessions(1) // 최대 허용 가능 세션 수, -1: 무제한 로그인 세션 허용
  .maxSessionsPreventsLogin(true) // ture: 동시 로그인 차단 - 현재 사용자 로그인 차단 (default: false - 이전 사용자 만료)
  .expiredUrl("/expired") // 세션이 만료된 경우 이동할 페이지
```

### 세션 고정 보호
- 공격자의 쿠키 조작을 방지
- `JSESSIONID` 쿠키값에 해당 세션 key를 건네주는 것에서 발생하는 문제
- 공격자가 심어놓은 `JSESSIONID` 값을 사용자에게 건네주고 사용자는 이것을 가지고 로그인 시도
- 같은 `JSESSIONID`를 공유하기에 공격자는 사용자가 인증한 것을 고스란히 같이 사용 가능
- 해결책: 로그인하면(인증) **새로운 세션 ID 생성**
```kotlin
http
  .sessionManagement()
  .sessionFixation()
  .changeSessionId()  // 세션 ID만 바뀜 (default)
//.none() // 인증 후 세션 관련 아무런 조치 X (취약)
//.migrateSession() // 새로운 session 생성, 새로운 세션 ID 발급 (이전의 세션들을 새로운 세션으로 옮김)
//.newSession() // 완전히 새로운 세션 생성(이전 세션 보존 X)
```

### 세션 정책
- `sessionCreationPolicy`
  - Always: 스프링 시큐리티가 항상 새로운 세션 생성
  - If_Required: 필요시 생성(기본값)
  - Never: 세션 생성하지는 않지만 이미 존재하면 사용
  - Stateless: 세션 생성하지 않고 존재해도 사용하지 않음(JWT)

<br>

## 📌 세션 제어 필터

### `SessionManagementFilter`
- 세션 관리
- 동시적 세션 제어
- 세션 고정 보호
- 세션 생성 정책

### `ConcurrentSessionFilter`
- **동시적 세션 제어를 같이 처리**
- 매 요청시 현재 사용자의 세션 만료 여부 체크
- 세션 만료시 즉시 만료 처리
```kotlin
session.isExpired() == true
// true인 경우 로그아웃 처리
// 즉시 오류 페이지 응답(This session has been expired)
```

### 절차중에서 중요한 부분 (`SessionManagementFilter`, `ConcurrentSessionFilter`)
- 인증 시도시 다음과 같은 순서로 진행
  - `SessionManagementFilter`
    - `ConcurrentSessionControlAuthenticationStrategy`
      - 여기서 동시적 세션 제어를 하게 된다. (현재 세션 count 기준으로 max 도달 여부 체크)
    - `ChangeSessionIdAuthenticationStrategy`
      - 세션 고정 보호 작업 (change session id)
    - `RegisterSessionAuthenticationStrategy`
      - 위 작업 수행 후 세션 정보 등록
  - `ConcurrentSessionFilter`
    - 접근 사용자의 세션이 만료되었는지 여부 체크후 만료시 즉시 로그아웃 처리
- 동시적 세션 제어 전략 2가지(이전 사용자 만료, 현재 사용자 인증 실패)
  - 이전 사용자 만료
    - `session.expireNow()` > 이전 사용자
    - 이전 사용자가 다시 접근할 때 `session.isExpired()` 수행 > `session.expireNow()` 참고
      - 이건 `ConcurrentSessionFilter`에서 수행
  - 현재 사용자 인증 실패
    - throw `SessionAuthenticationException`

<br>

## 📌 권한 설정과 표현식(인가, Authorization)
- 선언적 방식
  - URL
  - Method
- 동적 방식
  - URL
  - Method

### 선언적 방식
#### URL  
설정 파일에 해당
```kotlin
http
  .authorizeRequests()
  .antMatchers("/user").hasRole("USER")
  .antMatchers("/admin/pay").hasRole("ADMIN")
  .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
  .anyRequest().authenticated()
```
- 경로 설정은 구체적인 것부터 범위가 넓은 순으로 설정해야 한다.

#### Method  
해당 핸들러에 애노테이션 직접 적용
```kotlin
@PreAuthorize("hasRole('USER')")
fun user() { /*...*/ }
```

<br>

## 📌 인증/인가 API - ExceptionTranslationFilter, RequestCacheAwareFilter

- 해당 필터들은 다음 두 가지 예외를 처리
  - `AuthenticationException`: 인증 예외처리
  - `AccessDeniedException`: 인가 예외처리
- `FilterSecurityInterceptor`
  - Spring Security 맨 마지막 필터
  - 바로 앞이 `ExceptionTranslationFilter`
  - `try ~ catch`로 `FilterSecurityInterceptor`호출(인증, 인가 예외 throw)

### AuthenticationException (인증 예외)
- `AuthenticationEntryPoint` 호출 (interface)
  - 로그인 페이지 이동, 401 오류 코드 전달
- 인증 예외가 발생하기 전의 요청 정를 저장
  - `RequestCache`: 이전 요청 정보를 세션에 저장하고 이를 꺼내오는 메커니즘
    - `SavedRequest`: 구현체, 사용자가 요청했던 request parameter, header 저장

### AccessDeniedException (인가 예외)
- `AccessDeniedHandler`에서 예외 처리

<img width="1006" alt="Screen Shot 2022-06-18 at 3 05 58 PM" src="https://user-images.githubusercontent.com/41675375/174425263-49657538-6a45-4185-8393-b3fde7b75c89.png">

- `AuthenticationEntryPoint`: 로그인 페이지로 이동하기 전에 SecurityContext 인증 객체 null 처리
- `RequestCacheAwareFilter`: `saveRequest != null` 인 경우 저장된 savedRequest 내용을 다음 필터에 전달해주는 기능 제공 

```kotlin
http
  .exceptionHandling()
  .authenticationEntryPoint { _, response, _ ->
      response.sendRedirect("/login")
  }
  .accessDeniedHandler { _, response, _ ->
      response.sendRedirect("/denied")
  }
```

<br>

## 📌 사이트 간 요청 위조 - CSRF, CsrfFilter
- CSRF: 사이트 간 요청 위조
  - 사용자는 로그인하면 쿠키를 발급 받음(session id)
  - 공격자는 사용자에게 공격자 사이트 전달
  - 공격자 사이트 html 코드 중에 img를 통해서 공격자가 원하는 요청을 보냄(사용자 브라우저 통해서)
  - 서버는 사용자의 브라우저가 이미 인증(쿠키 발급)된 것으로 판단하고 공격자의 요청을 그대로 수행

### Form 인증 - CsrfFilter
- 모든 요청에 랜덤하게 생성된 토큰을 HTTP 헤더 or 파라미터로 요구
  - **POST, PUT, PATCH, DELETE**에 해당
  - `_csrf`(파라미터), `X-CSRF-TOKEN`(헤더)
- `클라이언트가 보낸 토큰` != `서버의 토큰` -> 요청 실패

```kotlin
// 기본적으로 csrf 설정되어 있음
http.csrf().disabled()
```
- csrf 설정시 모든 요청에 토큰 파라미터가 붙기 때문에 공격자가 건넨 html 내의 요청에서는 토큰이 붙어있을 수 없음