package io.beaniejoy.springvalidation.exception

import io.beaniejoy.springvalidation.dto.error.ErrorDto
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ExceptionControllerAdvice {

    companion object : KLogging()

    // @ModelAttribute (query param 에 대한 Valid 내용) > BindException
//    @ExceptionHandler(BindException::class)
    fun handleNotValidException(
        e: BindException,
        bindingResult: BindingResult
    ): ResponseEntity<ErrorDto> {
        logger.error("binding errors : ${bindingResult.fieldErrors}")

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

    // @Requestbody (request body에 대한 Valid 내용) > MethodArgumentNotValidException(extends BindException)
//    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleRequestBodyException(
        e: MethodArgumentNotValidException,
        bindingResult: BindingResult
    ): ResponseEntity<ErrorDto> {
        logger.error("request body errors : ${bindingResult.fieldErrors}")

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

    // @RequestParam, @PathVariable validation 에러
//    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(
        e: ConstraintViolationException
    ): ResponseEntity<ErrorDto> {
        logger.error("constraint violation error : ${e.message}")

        return ResponseEntity.badRequest()
            .body(
                ErrorDto(
                    description = e.message
                )
            )
    }
}