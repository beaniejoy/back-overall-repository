package io.beaniejoy.basicsecurity

import mu.KLogging
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class SecurityController {
    companion object: KLogging()

    @GetMapping("/")
    fun index(session: HttpSession): String {
        val authThreadLocal = SecurityContextHolder.getContext().authentication
        val context = session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) as SecurityContext
        val authSession = context.authentication
        // 인증된 후에 SC를 세션에 저장한다.
        // 인증된 이후에 api 호출시 session에서의 SC(authentication)와 ThreadLocal에서 꺼낸 SC(authentication)가 동일
        logger.info { "same authentication object? = ${authThreadLocal === authSession}" } // true
        logger.info { "ThreadLocal SC: ${authThreadLocal?.hashCode()}" }
        logger.info { "Session SC: ${authSession?.hashCode()}" }

        return "home"
    }

    @GetMapping("/thread")
    fun thread(): String {
        // 하위 스레드에서 꺼낸 Auth와 상위 스레드에서 꺼낸 Auth가 같은지는 Holder의 SC 저장 방식에 따라 달라짐
        val authMainThread = SecurityContextHolder.getContext().authentication
        Thread {
            val authSubThread = SecurityContextHolder.getContext().authentication
            // MODE_THREADLOCAL 방식이면 false
            // MODE_INHERITABLETHREADLOCAL 방식이면 true
            logger.info { "same authentication object? = ${authMainThread === authSubThread}" }
            logger.info { "Main Thread SC: ${authMainThread?.hashCode()}" }
            logger.info { "Sub Thread SC: ${authSubThread?.hashCode()}" }
        }.start()

        return "thread"
    }

    @GetMapping("/loginPage")
    fun loginPage(): String {
        return "loginPage"
    }

    @GetMapping("/user")
    fun user(): String {
        return "user"
    }

    @GetMapping("/admin/pay")
    fun adminPay(): String {
        return "adminPay"
    }

    @GetMapping("/admin/**")
    fun admin(): String {
        return "admin"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}