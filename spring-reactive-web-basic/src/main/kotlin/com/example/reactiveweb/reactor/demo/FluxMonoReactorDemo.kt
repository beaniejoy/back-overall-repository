package com.example.reactiveweb.reactor.demo

import com.example.reactiveweb.common.logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {
    Mono.just("Hello Reactor")
        .subscribe { message ->
            logger.info { message }
        }

    Flux.just("Hello", "Reactor")
        .map { it.lowercase() }
        .subscribe {
            logger.info { it }
        }
}