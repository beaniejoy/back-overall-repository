package io.beaniejoy.resttemplatebasic.controller

import mu.KLogging
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Max
import javax.validation.constraints.NotNull

@Validated
@RestController
@RequestMapping("/api/valid2")
class ValidationJavaXController {

    companion object : KLogging()

    @GetMapping("/get")
    fun getParam(
        @NotNull(message = "value 필수값입니다.") @RequestParam value: String?
    ): String? {
        return value
    }

    @GetMapping("/get/{id}")
    fun getParam(
        @Max(10, message = "최대 10까지 가능합니다.") @PathVariable("id") id: Long?
    ): Long? {
        return id
    }
}