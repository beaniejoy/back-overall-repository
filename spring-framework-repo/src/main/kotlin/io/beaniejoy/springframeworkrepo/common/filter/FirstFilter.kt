package io.beaniejoy.springframeworkrepo.common.filter

import mu.KLogging
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class FirstFilter : Filter {
    companion object : KLogging()

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        try {
            val req = request as HttpServletRequest
            logger.info { "#### [First Filter] request uri ${req.requestURI} ####" }

            chain.doFilter(request, response)
        } catch (e: Exception) {
            logger.error { "[First Filter] error: ${e.message}" }
        } finally {
            logger.info { "#### [First Filter] after doFilter ####" }
        }
    }
}