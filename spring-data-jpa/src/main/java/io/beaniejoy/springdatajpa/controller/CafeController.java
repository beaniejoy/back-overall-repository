package io.beaniejoy.springdatajpa.controller;

import io.beaniejoy.springdatajpa.dto.CafeRequestParam;
import io.beaniejoy.springdatajpa.dto.CafeResponse;
import io.beaniejoy.springdatajpa.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cafes")
public class CafeController {

    private final CafeService cafeService;

    @PostMapping("")
    public void postCafe() {
        cafeService.postCafe();
    }

    @GetMapping("")
    public Page<CafeResponse> getAllCafesDynamicParam(
            @ModelAttribute CafeRequestParam requestParam,
            @PageableDefault(page = 0, size = 20) Pageable pageable
    ) {
        return cafeService.getAllCafesWithParams(requestParam, pageable);
    }

    @GetMapping("/querydsl")
    public Page<CafeResponse> getAllCafesWithQuerydsl(
            @ModelAttribute CafeRequestParam requestParam,
            @PageableDefault(page = 0, size = 20) Pageable pageable
    ) {
        return cafeService.getAllCafesWithQuerydsl(requestParam, pageable);
    }
}
