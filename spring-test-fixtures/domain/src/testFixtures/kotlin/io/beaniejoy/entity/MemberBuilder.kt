package io.beaniejoy.entity

data class MemberBuilder(
    val id: Long = 1L,
    val email: String = "test@email.com",
    val password: String = "test123",
    val address: String? = "test address",
    val phoneNumber: String? = "01022223333",
) {
    fun build(): Member {
        return Member(
            email = email,
            password = password,
            address = address,
            phoneNumber = phoneNumber
        )
    }
}