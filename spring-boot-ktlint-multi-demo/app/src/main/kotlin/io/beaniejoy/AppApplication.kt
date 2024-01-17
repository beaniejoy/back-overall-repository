package io.beaniejoy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class AppApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(AppApplication::class.java)
        .properties("spring.config.name=application,application-persistence,application-domain")
        .run(*args)
}
