package io.beaniejoy.springframeworkrepo.filter.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/filter")
class SpringFilterController {

    @GetMapping("/test")
    fun filterTest(): String {
        return "ok"
    }
}