package io.beaniejoy.jacksonbindtest.dto.part04_kotlin

data class MemberRequestKtDto(
    var id: Long = 0L,
    var name: String? = null,
    var address: String? = null,
    var email: String? = null
)