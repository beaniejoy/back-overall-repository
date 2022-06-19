package io.beaniejoy.basicsecurity.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder

@Configuration
@EnableWebSecurity
class SecurityContextHolderConfig: WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .anyRequest().authenticated()

        http
            .formLogin()

        // SC 저장 방식 설정
        // - MODE_THREADLOCAL: 기본값, 쓰레드당 SC 할당
        // - MODE_INHERITABLETHREADLOCAL: 메인 스레드와 자식 스레드에 대해 동일 SC 유지
        // - MODE_GLOBAL: 애플리케이션에서 단 하나의 SC 저장
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
    }
}