package io.beaniejoy.springframeworkrepo.controller

import com.fasterxml.jackson.annotation.JsonIgnore
import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {
    companion object: KLogging()

    @GetMapping
    fun getTest(@RequestBody temp: Temp): Temp {
        logger.info { "temp $temp" }
        return Temp("beanie", "joy")
    }
}

data class Temp(
    val temp1: String,
    val temp2: String,
) {
    @JsonIgnore
    fun getTempGetter(): String {
        return "hello"
    }
}