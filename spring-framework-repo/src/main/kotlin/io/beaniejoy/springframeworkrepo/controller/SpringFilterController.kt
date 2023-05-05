package io.beaniejoy.springframeworkrepo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

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
}