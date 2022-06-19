package io.beaniejoy.basicsecurity.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

//@Configuration
//@EnableWebSecurity
class SecurityBaseConfig : WebSecurityConfigurerAdapter() {
//    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    override fun configure(http: HttpSecurity) {
        // authority 인가 정책
        http
            .authorizeRequests()
            .anyRequest().authenticated()

        // authenticate 인증 정책
        http
            .formLogin()    // formLogin 인증 방식 선택(Http)
            // .loginPage("/loginPage")    // 스프링 시큐리티 loginPage 경로 설정 (인증없이 접근 가능해야함)
            .defaultSuccessUrl("/")
            .failureUrl("/login")

            // formLogin에 대한 form tag 내부 설정
            .usernameParameter("userId")
            .passwordParameter("passwd")
            .loginProcessingUrl("/login_proc")

            // AuthenticationSuccessHandler
            .successHandler { _, response, authentication ->
                println("authentication - ${authentication.name}")
                response.sendRedirect("/")
            }
            // AuthenticationFailureHandler
//            .failureHandler { request, response, exception ->
//                println("exception ${exception.message}")
//                response.sendRedirect("/login")
//            }
            .permitAll()

        // 로그아웃 기능
        http
            .logout()
            .logoutUrl("/logout")
            // logout process 중 등록하고 싶은 작업 적용
            .addLogoutHandler { request, _, _ ->
                println("logout handler: session invalidate")
                val session = request.session
                session.invalidate()
            }
            // logout success 이후 후처리
            .logoutSuccessHandler { _, response, authentication ->
                println("logout success - ${authentication.name}")
                response.sendRedirect("/login")
            }
            .deleteCookies("remember-me") // 원하는 쿠키 삭제

        // remember-me 기능
        http
            .rememberMe()
            .rememberMeParameter("remember")
            .tokenValiditySeconds(60 * 60) // default 14일
            .alwaysRemember(false)  // true: remember-me를 활성화하지 않아도 항상 실행
            .userDetailsService(userDetailsService)

        http
            .sessionManagement()
            .invalidSessionUrl("/invalid") // 세션이 유효하지 않을 때 이동할 페이지
            .maximumSessions(1) // 최대 허용 가능 세션 수, -1: 무제한 로그인 세션 허용
            .maxSessionsPreventsLogin(true) // ture: 동시 로그인 차단 - 현재 사용자 로그인 차단 (default: false - 이전 사용자 만료)
            .expiredUrl("/expired") // 세션이 만료된 경우 이동할 페이지

        http
            .sessionManagement()
            .sessionFixation()
            .changeSessionId()  // 세션 ID만 바뀜 (default)
            //.none() // 인증 후 세션 관련 아무런 조치 X (취약)
            //.migrateSession() // 새로운 session 생성, 새로운 세션 ID 발급 (이전의 세션들을 새로운 세션으로 옮김)
            //.newSession() // 완전히 새로운 세션 생성(이전 세션 보존 X)
    }
}