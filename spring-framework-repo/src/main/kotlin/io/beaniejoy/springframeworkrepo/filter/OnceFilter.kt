package io.beaniejoy.springframeworkrepo.filter

import mu.KotlinLogging
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class OnceFilter : OncePerRequestFilter() {
    private val log = KotlinLogging.logger {}

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        log.info { "#### [*OnceFilter*] [${request.dispatcherType}] request uri ${request.requestURI} ####" }
        filterChain.doFilter(request, response)
        log.info { "#### [*OnceFilter*] after doFilter ####" }
    }
}