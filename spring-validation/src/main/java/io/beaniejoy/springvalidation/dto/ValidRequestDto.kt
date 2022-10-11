package io.beaniejoy.springvalidation.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

data class ValidRequestDto(
    @field:NotNull(message = "value는 필수 입력값입니다.")
    val value: String? = null,

    @field:NotNull(message = "createdAt은 필수 입력값입니다.")
    @field:PastOrPresent(message = "과거 날짜를 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val createdAt: LocalDate? = null,

    @field:NotNull(message = "number는 필수 입력값입니다.")
    val number: Long? = null
)