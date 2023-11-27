package io.beaniejoy.springframeworkrepo.controller

import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/session")
class SessionController {

    companion object: KLogging()

    @GetMapping
    fun testSession(request: HttpServletRequest) {
        val session = request.session

        val test = session.getAttribute("test")
        logger.info { "test session: $test" }
    }

    @PostMapping("/save")
    fun saveTestSession(request: HttpServletRequest): String {
        val session = request.session

        session.setAttribute("test", "beanie")

        return "ok"
    }
}