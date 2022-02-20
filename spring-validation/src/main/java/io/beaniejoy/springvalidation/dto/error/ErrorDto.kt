package io.beaniejoy.springvalidation.dto.error

data class ErrorDto(
    val message: String? = null,
    val validCode: String? = null,
    val description: String? = null
)