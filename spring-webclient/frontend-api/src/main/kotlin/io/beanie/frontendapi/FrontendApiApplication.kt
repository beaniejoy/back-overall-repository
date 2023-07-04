package io.beanie.frontendapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FrontendApiApplication

fun main(args: Array<String>) {
    runApplication<FrontendApiApplication>(*args)
}
