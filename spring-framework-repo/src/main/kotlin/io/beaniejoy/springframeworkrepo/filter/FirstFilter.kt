package io.beaniejoy.springframeworkrepo.filter

import mu.KLogging
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class FirstFilter : Filter {
    companion object : KLogging()

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        logger.info { "#### [FirstFilter] [${req.dispatcherType}] request uri ${req.requestURI} ####" }

        chain.doFilter(request, response)
        logger.info { "#### [FirstFilter] after doFilter ####" }
    }
}