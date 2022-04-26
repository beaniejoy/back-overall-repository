package io.beaniejoy.springdocexample.domain.member

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "MemberController", description = "member 도메인 API")
@RestController
@RequestMapping("/member")
class MemberController {

    @Operation(summary = "회원 조회")
    @GetMapping("/{id}")
    fun getMember(@PathVariable("id") id: Long): Long {
        return id
    }
}