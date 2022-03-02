package io.beaniejoy.springvalidation.controller

import mu.KLogging
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Max
import javax.validation.constraints.NotNull

@Validated
@RestController
@RequestMapping("/api/valid2")
class ValidationJavaXController {

    companion object : KLogging()

    /*
    기본적으로 ConstraintViolationException (500 응답코드)
    BindingResult 반환 X
     */
    @GetMapping("/get")
    fun getParam(
        @NotNull(message = "value 필수값입니다.") @RequestParam value: Int?
    ): Int? {
//        if (bindingResult.hasErrors()) {
//            logger.error("errors : ${bindingResult.fieldErrors}")
//            throw RuntimeException("errors ${bindingResult.fieldErrors}")
//        }

        return value
    }

    /*
    기본적으로 ConstraintViolationException (500 응답코드)
    BindingResult 반환 X
     */
    @GetMapping("/path/{id}")
    fun getParam(
        @Max(10, message = "최대 10까지 가능합니다.") @PathVariable("id") id: Long?
    ): Long? {
        return id
    }
}