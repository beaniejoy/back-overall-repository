package io.beaniejoy.springdocexample.domain.member.entity

enum class Role(
    val description: String
) {
    ADMIN("관리자"), USER("사용자")
}