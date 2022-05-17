package io.beaniejoy.basicsecurity

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SecurityController {

    @GetMapping("/")
    fun index(): String {
        return "home"
    }

    @GetMapping("/loginPage")
    fun loginPage(): String {
        return "loginPage"
    }
}