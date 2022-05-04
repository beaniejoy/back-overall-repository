package io.beaniejoy.springdocexample.domain.member.dto

import javax.validation.constraints.NotNull

data class MemberRegisterRequestDto(
    @field:NotNull(message = "이메일은 필수 입력 값입니다.")
    val email: String? = null,

    @field:NotNull(message = "비밀번호는 필수 입력 값입니다.")
    val password: String? = null,

)