package io.beaniejoy.springdocexample.domain.member

import io.beaniejoy.springdocexample.domain.member.dto.MemberRegisterRequestDto
import io.beaniejoy.springdocexample.domain.member.dto.MemberResponseDto
import io.beaniejoy.springdocexample.domain.member.entity.Role
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Tag(name = "MemberController", description = "member 도메인 API")
@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService
) {
    companion object: KLogging()

    @Operation(summary = "회원 가입 API")
    @PostMapping("/join")
    fun registerMember(
        @RequestBody memberRegisterRequestDto: MemberRegisterRequestDto,
        bindingResult: BindingResult
    ): Long {
        if (bindingResult.hasErrors()) {
            logger.error { "Binding Errors: ${bindingResult.fieldErrors}" }
            throw RuntimeException("Binding Error: ${bindingResult.fieldError?.defaultMessage}")
        }

        return memberService.register(
            email = memberRegisterRequestDto.email!!,
            password = memberRegisterRequestDto.password!!
        )
    }

    @Operation(summary = "로그인 API")
    @PostMapping("/login")
    fun authenticate() {

    }

    @Operation(summary = "회원 조회")
    @GetMapping("/{id}")
    fun getMember(@PathVariable("id") id: Long): Long {
        return id
    }

    @Operation(summary = "회원 조건 조회")
    @GetMapping("")
    fun getMemberList(
        @Parameter(
            name = "role",
            description = "사용자 권한",
            schema = Schema(implementation = Role::class, required = true)
        )
        @RequestParam("role") role: Role
    ): List<MemberResponseDto> {
        return memberService.getMemberListWith(role)
    }
}