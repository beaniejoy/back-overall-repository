package io.beaniejoy.springdocexample.domain.cafe.dto

data class CafeResponseDto(
    val id: Long,
    val name: String,
    val address: String,
    val menuItems: List<MenuItemDto>,
    val phoneNumber: String
)