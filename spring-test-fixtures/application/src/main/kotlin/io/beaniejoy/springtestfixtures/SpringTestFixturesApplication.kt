package io.beaniejoy.springtestfixtures

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringTestFixturesApplication

fun main(args: Array<String>) {
    runApplication<SpringTestFixturesApplication>(*args)
}
