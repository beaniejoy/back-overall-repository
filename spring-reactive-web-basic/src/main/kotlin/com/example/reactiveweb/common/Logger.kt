package com.example.reactiveweb.common

import mu.KLogger
import mu.KotlinLogging

val logger: KLogger = KotlinLogging.logger {}

fun KLogger.doOnNext(msg: () -> Any?) {
    this.info { "doOnNext >> ${msg()}" }
}

fun KLogger.doOnRequest(msg: () -> Any?) {
    this.info { "doOnRequest >> ${msg()}" }
}

fun KLogger.onNext(msg: () -> Any?) {
    this.info { "onNext >> ${msg()}" }
}
