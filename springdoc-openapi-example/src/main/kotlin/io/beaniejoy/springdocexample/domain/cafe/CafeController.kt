package io.beaniejoy.springdocexample.domain.cafe

import io.beaniejoy.springdocexample.common.ApiPageable
import io.beaniejoy.springdocexample.domain.cafe.dto.CafeRegisterRequestDto
import io.beaniejoy.springdocexample.domain.cafe.dto.CafeResponseDto
import io.beaniejoy.springdocexample.domain.cafe.dto.CafeSearchRequestDto
import io.beaniejoy.springdocexample.domain.cafe.dto.CafeUpdateRequestDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Tag(name = "CafeController", description = "cafe 도메인 API")
@RestController
@RequestMapping("/cafe")
class CafeController(
    private val cafeService: CafeService
) {
    companion object : KLogging()

    @Operation(summary = "단일 카페 조회 API")
    @GetMapping("/{id}")
    fun getCafe(@PathVariable("id") id: Long): CafeResponseDto {
        return cafeService.getCafeById(id)
    }

    @ApiPageable
    @Operation(summary = "카페 리스트 조건 검색 API")
    @GetMapping("/list")
    fun getCafeListWithCondition(
        @ParameterObject
        @ModelAttribute cafeSearchRequestDto: CafeSearchRequestDto,
        @Parameter(hidden = true)
        @PageableDefault(page = 0, size = 20) pageable: Pageable
    ): List<CafeResponseDto> {
        return cafeService.getCafeListWith(
            id = cafeSearchRequestDto.id,
            name = cafeSearchRequestDto.name,
            address = cafeSearchRequestDto.address,
            pageable = pageable
        )
    }

    @Operation(summary = "신규 카페 등록 API")
    @PostMapping
    fun registerNewCafe(
        @RequestBody cafeRegisterRequestDto: CafeRegisterRequestDto,
        bindingResult: BindingResult
    ) {
        if (bindingResult.hasErrors()) {
            logger.error { "Binding Errors: ${bindingResult.fieldErrors}" }
            throw RuntimeException("Binding Error: ${bindingResult.fieldError?.defaultMessage}")
        }

        cafeService.registerCafe(
            name = cafeRegisterRequestDto.name!!,
            address = cafeRegisterRequestDto.address!!,
            menuItems = cafeRegisterRequestDto.menuItems,
            phoneNumber = cafeRegisterRequestDto.phoneNumber
        )
    }

    @Operation(summary = "카페 등록 API")
    @PutMapping("/{id}")
    fun updateCafe(
        @PathVariable("id") id: Long,
        @RequestBody cafeUpdateRequestDto: CafeUpdateRequestDto
    ): Long {
        return cafeService.updateCafe(
            id = id,
            name = cafeUpdateRequestDto.name,
            address = cafeUpdateRequestDto.address,
            phoneNumber = cafeUpdateRequestDto.phoneNumber
        )
    }
}