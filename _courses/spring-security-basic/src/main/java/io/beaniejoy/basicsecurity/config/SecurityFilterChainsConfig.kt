package io.beaniejoy.basicsecurity.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

// Order: 순서 중요!
//@Order(1)
//@Configuration
//@EnableWebSecurity
class SecurityFilterChainsConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .antMatcher("/admin/**")
            .authorizeRequests()
            .anyRequest().authenticated()

            .and()
            .httpBasic()
    }
}

//@Order(2)
//@Configuration
//@EnableWebSecurity
class SecurityFilterChainsConfig2 : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .anyRequest().permitAll()

            .and()
            .formLogin()
    }
}