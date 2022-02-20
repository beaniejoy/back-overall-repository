package io.beaniejoy.resttemplatebasic.controller

import io.beaniejoy.resttemplatebasic.entity.ValidRequestDto
import mu.KLogging
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/valid")
class ValidationController {

    companion object : KLogging()

    @GetMapping("/get")
    fun getParam(
        @Valid @ModelAttribute validRequestDto: ValidRequestDto
    ): ValidRequestDto {
//        if (bindingResult.hasErrors()) {
//            logger.error("errors : ${bindingResult.fieldErrors}")
//            throw RuntimeException("errors ${bindingResult.fieldErrors}")
//        }

        return validRequestDto
    }

    @PostMapping("/post")
    fun postRequestBody(
        @Valid @RequestBody validRequestDto: ValidRequestDto
    ): ValidRequestDto {
//        if (bindingResult.hasErrors()) {
//            logger.error("errors : ${bindingResult.fieldErrors}")
//            throw RuntimeException("errors ${bindingResult.fieldErrors}")
//        }

        return validRequestDto
    }
}