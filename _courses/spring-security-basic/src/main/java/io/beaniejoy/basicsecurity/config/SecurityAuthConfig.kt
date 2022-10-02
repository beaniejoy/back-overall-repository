package io.beaniejoy.basicsecurity.config

import mu.KLogging
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//@Configuration
//@EnableWebSecurity
class SecurityAuthConfig : WebSecurityConfigurerAdapter() {
    companion object: KLogging()

    // 권한 설정(인가 설정)
    override fun configure(auth: AuthenticationManagerBuilder) {
        // setting user based on in-memory (yaml 설정파일에서도 등록 가능)
        auth.inMemoryAuthentication().withUser("user").password("{noop}1111").roles("USER")
        auth.inMemoryAuthentication().withUser("sys").password("{noop}1111").roles("SYS")
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1111").roles("ADMIN")
    }

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/user").hasRole("USER")
            .antMatchers("/admin/pay").hasRole("ADMIN")
            .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
            .anyRequest().authenticated()

        http
            .formLogin()
            .successHandler { request, response, _ -> // 사용자가 요청했던 request를 session에 저장하는 기능 제공
                // 인증 전에 이미 세션에 요청정보가 저장되어 있는 상황
                val requestCache: RequestCache = HttpSessionRequestCache()
                val savedRequest = requestCache.getRequest(request, response)
                val redirectUrl = savedRequest.redirectUrl

                logger.info { "SavedRequest redirect url: ${redirectUrl}" }
                response.sendRedirect(redirectUrl)
            }

        http
            .exceptionHandling()
//            .authenticationEntryPoint { _, response, _ ->
//                response.sendRedirect("/login")
//            }
            .accessDeniedHandler { _, response, _ ->
                response.sendRedirect("/denied")
            }
    }
}