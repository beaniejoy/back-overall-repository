package com.example.reactiveweb.reactor.demo.error

import com.example.reactiveweb.common.doOnNext
import com.example.reactiveweb.common.logger
import reactor.core.publisher.Flux

// https://medium.com/@odysseymoon/spring-webflux%EC%97%90%EC%84%9C-error-%EC%B2%98%EB%A6%AC%EC%99%80-retry-%EC%A0%84%EB%9E%B5-a6bd2c024f6f
fun main() {
//    errorResume()
//    errorContinue()
    doOnError()
}

/**
 * result: 10, -3, -4
 * error 발생한 지점(2)부터 실행 X (에러 발생시 publish 종료)
 * onErrorResume 반환하는 Flux publisher로 전환해서 subscribe 진행
 */
fun errorResume() {
    logger.info { "errorResume" }

    Flux.just(1, 2, 3, 4)
        .flatMap { Flux.just(errorHandlingService.tenTimesExcept(it, 2)) }
        .onErrorResume(RuntimeException::class.java) { throwable ->
            logger.error { throwable.message }
            Flux.just(-3, -4)
        }
        .subscribe {
            logger.doOnNext { it }
        }
}

/**
 * result: 10, errorContinue(2), 30, 40
 * error 발생한 대상에 대해 onErrorContinue 실행
 * 에러 발생한 지점 이후로 계속 subscribe 진행
 */
fun errorContinue() {
    logger.info { "errorContinue" }

    Flux.just(1, 2, 3, 4)
        .flatMap { Flux.just(errorHandlingService.tenTimesExcept(it, 2)) }
        .onErrorContinue { throwable, value ->
            logger.error { "$value >> ${throwable.message}" }
        }
        .subscribe {
            logger.doOnNext { it }
        }
}

fun doOnError() {
    logger.info { "errorContinue" }

    Flux.just(1, 2, 3, 4)
        .flatMap { Flux.just(errorHandlingService.tenTimesExcept(it, 2)) }
        .doOnError {
            logger.error { "doOnError >> ${it.message}" }
        }
        .subscribe {
            logger.doOnNext { it }
        }
}