package io.beaniejoy.springframeworkrepo.config

import io.beaniejoy.springframeworkrepo.filter.FirstFilter
import io.beaniejoy.springframeworkrepo.filter.OnceFilter
import io.beaniejoy.springframeworkrepo.filter.SecondFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.servlet.DispatcherType
import javax.servlet.Filter

@Configuration
class FilterConfig {

    @Bean
    fun firstFilter(): FilterRegistrationBean<Filter> {
        return FilterRegistrationBean<Filter>().apply {
            this.filter = FirstFilter()
            this.order = 1
            this.setDispatcherTypes(DispatcherType.REQUEST)
        }
    }

    @Bean
    fun secondFilter(): FilterRegistrationBean<Filter> {
        return FilterRegistrationBean<Filter>().apply {
            this.filter = SecondFilter()
            this.order = 2
            this.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR)
        }
    }

    /**
     * OncePerRequestFilter 중복호출 방지 테스트
     * setDispatcherTypes으로 따로 등록을 해도 한 번 호출되는 것을 확인
     */
    @Bean
    fun onceFilter(): FilterRegistrationBean<Filter> {
        return FilterRegistrationBean<Filter>().apply {
            this.filter = OnceFilter()
            this.order = 3
            this.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR)
        }
    }
}