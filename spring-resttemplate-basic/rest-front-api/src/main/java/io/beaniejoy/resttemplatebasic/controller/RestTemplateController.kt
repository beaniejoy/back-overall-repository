package io.beaniejoy.resttemplatebasic.controller

import io.beaniejoy.resttemplatebasic.application.RestTemplateFacade
import io.beaniejoy.resttemplatebasic.entity.ExampleGetterDto
import io.beaniejoy.resttemplatebasic.entity.MemberDto
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rest")
class RestTemplateController(private val restTemplateFacade: RestTemplateFacade) {
    private val logger = LoggerFactory.getLogger(RestTemplateController::class.java)

    @GetMapping("/member/object")
    fun getMemberForObjectApi(): MemberDto {
        return restTemplateFacade.member
    }

    @PostMapping("/member/post")
    fun postMember(@RequestBody memberDto: MemberDto) {
        logger.info(memberDto.toString())
    }

    @GetMapping("/example/getter")
    fun getExampleGetter(): ExampleGetterDto {
        return restTemplateFacade.exampleGetter
    }

    @PostMapping("/example/getter/post")
    fun postExampleGetter(@RequestBody exampleGetterDto: ExampleGetterDto) {
        logger.info(exampleGetterDto.toString())
    }
}