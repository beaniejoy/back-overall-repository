package io.beaniejoy.springframeworkrepo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/api/filter")
class SpringFilterController {

    @ResponseBody
    @GetMapping("/test")
    fun filterTest(): String {
        return "ok"
    }

    @GetMapping("/forward")
    fun forward(): String {
        return "forward:/api/filter/test"
    }

    @GetMapping("/error")
    fun error() {
        throw RuntimeException("error test")
    }

    @GetMapping("/error2")
    fun error2(response: HttpServletResponse) {
        response.sendError(404)
    }
}