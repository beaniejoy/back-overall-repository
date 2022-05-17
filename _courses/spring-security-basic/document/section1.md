# Section 1. 스프링 시큐리티 기본 API 및 Filter 이해

## :pushpin: 사용자 정의 보안 기능 구현
- `WebSecurityConfigurerAdapter`: 스프링 시큐리티 웹 보안 기능 초기화 및 설정
    - `HttpSecurity`: 세부적인 보안 기능 설정할 수 있는 API 제공
    - SecurityConfig 설정 클래스가 adapter를 상속받아서 시큐리티 내용 설정
    - 여기서 초기화 작업 진행 후 Config 파일에 등록한 사용자정의 설정 내용 적용

## Form Login 인증
- `SecurityConfig.kt` 참고

## Form Login 인증 필터
- `UsernamePasswordAuthenticationFilter`
  - 인증 관련 작업을 진행하는 filter
  - `AntPathRequestMatcher("/login")`: 인증 처리 경로가 맞는지 체크
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

## Logout 처리, LogoutFilter
- `SecurityConfig.kt` 참고
- 세션 무효화, 인증토큰 삭제(Authentication 객체, SecurityContext), 쿠키정보 삭제, 로그인 페이지로 redirect

## Remember Me 인증
- 세션이 만료되고 웹 브라우저가 종료된 후에도 애플리케이션이 사용자를 기억하는 기능
- 쿠키를 사용
- 원래는 로그인을 통해 인증을 하면 `JSESSIONID`를 통해 이후 요청에 대해서 인증을 처리해준다.
- remember-me 활성화시 `JSESSIONID`, `remember-me` cookie도 생성
  - 이 때는 `JESSIONID`를 지워도 `remember-me` cookie 내용을 가지고 다시 인증 처리
