package io.beaniejoy.springframeworkrepo.controller

import io.beaniejoy.springframeworkrepo.mapper.RequestDtoMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mapstruct")
class MapStructController(
    private val requestDtoMapper: RequestDtoMapper
) {
    @GetMapping("")
    fun test() {

    }
}