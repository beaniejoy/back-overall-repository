package io.beaniejoy.jacksonbindtest.controller

import io.beaniejoy.jacksonbindtest.dto.part04_kotlin.MemberRequestKtDto1
import io.beaniejoy.jacksonbindtest.dto.part04_kotlin.MemberRequestKtDto2
import mu.KLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member/kt")
class MemberKtController {
    companion object: KLogging()

    @PostMapping("/one")
    fun postMember1(@RequestBody requestDto: MemberRequestKtDto1): MemberRequestKtDto1 {
        logger.info { requestDto }
        return requestDto
    }

    @PostMapping("/two")
    fun postMember2(@RequestBody requestDto: MemberRequestKtDto2): MemberRequestKtDto2 {
        logger.info { requestDto }
        return requestDto
    }
}