# Section 1. 스프링 시큐리티 기본 API 및 Filter 이해

## 사용자 정의 보안 기능 구현
- `WebSecurityConfigurerAdapter`: 스프링 시큐리티 웹 보안 기능 초기화 및 설정
    - `HttpSecurity`: 세부적인 보안 기능 설정할 수 있는 API 제공
    - SecurityConfig 설정 클래스가 adapter를 상속받아서 시큐리티 내용 설정
    - 여기서 초기화 작업 진행 후 Config 파일에 등록한 사용자정의 설정 내용 적용

### Form Login 인증
- SecurityConfig.kt 참고

### Form Login 인증 필터
- `UsernamePasswordAuthenticationFilter`
  - 인증 관련 작업을 진행하는 filter
  - `AntPathRequestMatcher("/login")`: 인증 처리 경로가 맞는지 체크
  - `Authentication`(인증 전) - username, password
  - `AuthenticationManager` -> `AuthenticationProvider` 위임 (여기서 실질적인 인증 성공 여부 처리 진행)
    - 인증 실패시 `AuthenticationException` throw
    - 성공시 `AuthenticationManager`에게 인증성공 응답
  - `Authentication`(인증 성공 후) - User 객체, Authorities (권한 정보)
  - `SecurityContext`에 저장 -> session에도 저장
  - `SuccessHandler`: 후속 처
- `FilterChainProxy`
  - filter들을 관리하는 bean
  - Security를 등록하면 관련 filter들도 등록된다.