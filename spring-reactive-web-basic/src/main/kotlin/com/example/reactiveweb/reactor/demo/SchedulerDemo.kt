package com.example.reactiveweb.reactor.demo

import com.example.reactiveweb.common.doOnNext
import com.example.reactiveweb.common.logger
import com.example.reactiveweb.common.onNext
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * Schedulers.parallel() : 여러 개 쓰레드를 할당해서 동시 작업 수행 가능??
 *
 */
fun main() {
    Flux.range(1, 10000)
        .doOnNext { logger.doOnNext { "1 >> $it" } }
        .publishOn(Schedulers.parallel())
        .doOnNext { logger.doOnNext { "2 >> $it" } }
        .publishOn(Schedulers.parallel())
        .map { it * 10 }
        .doOnNext { logger.doOnNext { "3 >> $it" } }
        .subscribe { logger.onNext { it } }
}