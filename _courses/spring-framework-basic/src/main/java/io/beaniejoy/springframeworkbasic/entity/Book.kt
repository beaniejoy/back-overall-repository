package io.beaniejoy.springframeworkbasic.entity

import java.math.BigDecimal
import java.time.LocalDateTime

class Book(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val description: String,
    val createdAt: LocalDateTime,
    val createdBy: String
)