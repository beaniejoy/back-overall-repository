package io.beaniejoy.springframeworkbasic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.jvm.JvmStatic

@SpringBootApplication
class SpringFrameworkBasicApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(SpringFrameworkBasicApplication::class.java, *args)
        }
    }
}