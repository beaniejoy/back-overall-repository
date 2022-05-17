package io.beaniejoy.basicsecurity.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.logout.LogoutHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
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
            .failureHandler { request, response, exception ->
                println("exception ${exception.message}")
                response.sendRedirect("/login")
            }
            .permitAll()
            .and()

            .logout()
            .logoutUrl("/logout")
            // logout process 중 등록하고 싶은 작업 적용
            .addLogoutHandler { request, _, _ ->
                println("session invalidate")
                val session = request.session
                session.invalidate()
            }
            // logout success 이후 후처리
            .logoutSuccessHandler { _, response, authentication ->
                println("logout success - ${authentication.name}")
                response.sendRedirect("/login")
            }
            .deleteCookies("remember-me") // 원하는 쿠키 삭제
            .and()

            .rememberMe()
            .rememberMeParameter("remember")
            .tokenValiditySeconds(60 * 60) // default 14일
            .alwaysRemember(false)  // true: remember-me를 활성화하지 않아도 항상 실행
            .userDetailsService(userDetailsService)
    }
}