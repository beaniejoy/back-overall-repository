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
- 요청에 따라 `RequestMatcher`와 매칭되는 필터가 작동하도록 함
