package io.beaniejoy.springdocexample.domain.member.dto

import io.beaniejoy.springdocexample.domain.member.entity.Role

data class MemberResponseDto(
    val id: Long,
    val email: String,
    val role: Role
)