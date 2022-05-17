package io.beaniejoy.basicsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringSecurityBasicApplication

fun main(args: Array<String>) {
    runApplication<SpringSecurityBasicApplication>(*args)
}