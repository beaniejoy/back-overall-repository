package com.example.reactiveweb.reactor.demo

import com.example.reactiveweb.common.doOnNext
import com.example.reactiveweb.common.logger
import com.example.reactiveweb.common.onNext
import com.example.reactiveweb.reactor.demo.error.errorHandlingService
import reactor.core.publisher.Flux
import reactor.util.retry.Retry
import java.time.Duration


fun main() {
    retryTest()
}

fun repeatTest() {
    Flux.range(1, 4)
        .doOnNext {
            logger.doOnNext { "Emitted :: $it" }
            errorHandlingService.tenTimesExcept(it, 2)
        }
        .onErrorContinue { throwable, value ->
            logger.error { "$value >> ${throwable.message}" }
        }
        .repeat(1)
        .subscribe { logger.onNext { "Received :: $it" } }
}

fun retryTest() {
    Flux.range(1, 5)
        .doOnNext {
            logger.doOnNext { "Emitted :: $it" }
            errorHandlingService.tenTimesExcept(it, 3)
        }
        .doOnError { logger.error { "doOnError >> ${it.message}" } }
        .retryWhen(
            Retry.backoff(3, Duration.ofSeconds(2)).transientErrors(true)
        )
        .onErrorContinue { throwable, value ->
            logger.error { "$value >> onErrorContinue" }
        }
        .subscribe { logger.onNext { "Received :: $it" } }
}