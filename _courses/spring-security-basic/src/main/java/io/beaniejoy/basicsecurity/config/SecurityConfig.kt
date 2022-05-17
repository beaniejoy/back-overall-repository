package io.beaniejoy.basicsecurity.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
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
                println("authentication ${authentication.name}")
                response.sendRedirect("/")
            }
            // AuthenticationFailureHandler
            .failureHandler { request, response, exception ->
                println("exception ${exception.message}")
                response.sendRedirect("/login")
            }
            .permitAll()
    }
}