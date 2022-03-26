package io.beaniejoy.resttemplatebasic.controller

import io.beaniejoy.resttemplatebasic.application.RestTemplateFacade
import io.beaniejoy.resttemplatebasic.dto.MemberRequestDto
import io.beaniejoy.resttemplatebasic.entity.MemberDto
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/member")
class MemberController(private val restTemplateFacade: RestTemplateFacade) {
    private val logger = LoggerFactory.getLogger(MemberController::class.java)

    @GetMapping("/{id}")
    fun getMember(@PathVariable("id") id: Long): MemberDto {
        return restTemplateFacade.member
    }

    @PostMapping("")
    fun postMember(@RequestBody memberRequestDto: MemberRequestDto): MemberRequestDto {
        logger.info(memberRequestDto.toString())
        return memberRequestDto
    }
}