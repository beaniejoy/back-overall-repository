package io.beaniejoy.springframeworkrepo.config

import io.beaniejoy.springframeworkrepo.common.filter.FirstFilter
import io.beaniejoy.springframeworkrepo.common.filter.SecondFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.Filter

@Configuration
class FilterConfig : WebMvcConfigurer {

    @Bean
    fun firstFilter(): FilterRegistrationBean<Filter> {
         return FilterRegistrationBean<Filter>().apply {
            this.filter = FirstFilter()
            this.order = 1
        }
    }

    @Bean
    fun secondFilter(): FilterRegistrationBean<Filter> {
        return FilterRegistrationBean<Filter>().apply {
            this.filter = SecondFilter()
            this.order = 1
        }
    }
}