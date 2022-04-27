package io.beaniejoy.springdocexample.domain.cafe.dto

import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull

data class MenuItemDto(
    @field:NotNull(message = "카페 메뉴 이름은 필수 입력 값입니다.")
    val name: String? = null,

    @field:NotNull(message = "카페 메뉴 가격은 필수 입력 값입니다.")
    @field:DecimalMin(value = "0")
    @field:Digits(integer = 7, fraction = 0)
    val price: BigDecimal? = null
)
