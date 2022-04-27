package io.beaniejoy.springdocexample.domain.cafe.dto

data class CafeSearchRequestDto(
    val id: Long? = null,
    val name: String? = null,
    val address: String? = null
)
