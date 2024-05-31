package com.example.reactiveweb.demo

import com.example.reactiveweb.common.doOnNext
import com.example.reactiveweb.common.doOnRequest
import com.example.reactiveweb.common.logger
import com.example.reactiveweb.common.onNext
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

/**
 * request 개수를 요청하기 전까지 publisher는 발행을 하지 않는다.
 */
fun main() {
    var count = 0
    Flux.range(1, 1000)
        .doOnNext { logger.doOnNext { it } }
        .doOnRequest { logger.doOnRequest { it } }
        .subscribe(object : BaseSubscriber<Int>() {
            override fun hookOnSubscribe(subscription: Subscription) {
                request(10)
            }

            override fun hookOnNext(value: Int) {
                count++
                logger.onNext { value }
                if (count == 10) {
                    Thread.sleep(2000)
                    request(10)
                    count = 0
                }
            }
        })
}