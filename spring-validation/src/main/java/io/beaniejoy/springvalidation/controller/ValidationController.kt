package io.beaniejoy.springvalidation.controller

import io.beaniejoy.springvalidation.dto.ValidRequestDto
import mu.KLogging
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/valid")
class ValidationController {

    companion object : KLogging()
    
    /*
    ModelAttribute > type 변환, validation 관련된 내용을 BindingResult에 담아줌
    그렇기에 Controller 단에서 Binding Error 관련된 내용을 바로 400에러로 반환하지 않고 처리 가능
    기본적으로 BindException 반환 (400 응답코드)
    */
    @GetMapping("/get")
    fun getParam(
        @Valid @ModelAttribute validRequestDto: ValidRequestDto,
        bindingResult: BindingResult
    ): ValidRequestDto {
        if (bindingResult.hasErrors()) {
            logger.error("errors : ${bindingResult.fieldErrors}")
            throw RuntimeException("errors ${bindingResult.fieldErrors}")
        }

        return validRequestDto
    }

    /*
    기본적으로 MethodArgumentNotValidException 반환 (400 응답코드)
    */
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