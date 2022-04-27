package io.beaniejoy.springdocexample.domain.cafe.dto

import java.math.BigDecimal

data class MenuItemDto(
    val name: String? = null,
    val price: BigDecimal? = null
)
