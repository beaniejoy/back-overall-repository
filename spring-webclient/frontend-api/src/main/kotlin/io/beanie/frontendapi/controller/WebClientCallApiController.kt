package io.beanie.frontendapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/webclient")
class WebClientCallApiController {
    @GetMapping("/get")
    fun getTest() {

    }
}