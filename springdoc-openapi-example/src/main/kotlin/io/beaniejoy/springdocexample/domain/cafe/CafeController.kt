package io.beaniejoy.springdocexample.domain.cafe

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "CafeController", description = "cafe 도메인 API")
@RestController
@RequestMapping("/cafe")
class CafeController {

    @Operation(summary = "카페 조회")
    @GetMapping("/{id}")
    fun getCafe(@PathVariable("id") id: Long): Long {
        return id
    }
}