package io.beaniejoy.resttemplatebasic.exception

import io.beaniejoy.resttemplatebasic.error.ErrorDto
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    companion object : KLogging()

    // @ModelAttribute (query param 에 대한 Valid 내용) > BindException
    // @Requestbody (request body에 대한 Valid 내용) > MethodArgumentNotValidException(extends BindException)
//    @ExceptionHandler(BindException::class)
    fun handleNotValidException(
        e: BindException,
        bindingResult: BindingResult
    ): ResponseEntity<ErrorDto> {
        logger.error("validation errors : ${bindingResult.fieldErrors}")

        val defaultMessage = bindingResult.fieldError?.defaultMessage
        val code = bindingResult.fieldError?.code

        return ResponseEntity.badRequest()
            .body(
                ErrorDto(
                    message = defaultMessage,
                    validCode = code,
                    description = e.message
                )
            )
    }
}