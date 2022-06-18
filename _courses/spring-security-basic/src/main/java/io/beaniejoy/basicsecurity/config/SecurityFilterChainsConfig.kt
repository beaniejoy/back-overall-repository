package io.beaniejoy.basicsecurity.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
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

@Configuration
@EnableWebSecurity
class SecurityFilterChainsConfig2 : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .anyRequest().permitAll()

            .and()
            .formLogin()
    }
}