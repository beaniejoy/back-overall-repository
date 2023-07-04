package io.beanie.frontendapi.caller

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class WebClientCaller {

    fun get() {
        WebClient.create()
    }
}