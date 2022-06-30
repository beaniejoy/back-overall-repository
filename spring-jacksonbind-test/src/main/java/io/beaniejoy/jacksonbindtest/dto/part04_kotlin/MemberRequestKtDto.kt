package io.beaniejoy.jacksonbindtest.dto.part04_kotlin

import com.fasterxml.jackson.annotation.JsonProperty

data class MemberRequestKtDto1(
    var id: Long = 0L,
    var name: String,
    var address: String,
    var email: String
)

// private 인자 일부 존재, getter, setter 생성 X
data class MemberRequestKtDto2(
    var id: Long = 0L,
    var name: String? = null,

    // JsonProperty 통해 private으로 지정된 인자도 binding 가능
    // @field를 통해 필드에 어노테이션 적용해야 함
//    @field:JsonProperty("address")
    private var address: String,
//    @field:JsonProperty("email")
    private var email: String
)