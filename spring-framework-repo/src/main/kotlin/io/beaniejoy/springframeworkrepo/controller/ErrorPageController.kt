package io.beaniejoy.springframeworkrepo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/error-page")
class ErrorPageController {

    @GetMapping("/404")
    fun notFound(): String {
        return "404"
    }

    @GetMapping("/500")
    fun serverError(): String {
        return "500"
    }
}