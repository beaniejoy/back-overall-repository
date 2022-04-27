package io.beaniejoy.springdocexample.domain.cafe.dto

import javax.validation.constraints.NotNull

data class CafeUpdateRequestDto(
    val name: String? = null,

    val address: String? = null,

    val phoneNumber: String? = null
)
