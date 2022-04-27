package io.beaniejoy.springdocexample.domain.cafe.dto

import javax.validation.constraints.NotNull

data class CafeRegisterRequestDto(
    @field:NotNull(message = "카페 이름은 필수 입력 값입니다.")
    val name: String? = null,

    @field:NotNull(message = "카페 주소는 필수 입력 값입니다.")
    val address: String? = null,

    val menuItems: List<MenuItemDto> = listOf(),

    val phoneNumber: String? = null
)
