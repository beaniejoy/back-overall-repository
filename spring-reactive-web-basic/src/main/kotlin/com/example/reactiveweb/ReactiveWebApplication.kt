package com.example.reactiveweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveWebApplication

fun main(args: Array<String>) {
    runApplication<ReactiveWebApplication>(*args)
}
