package io.beaniejoy.springdatajpa;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cafes")
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("")
    public Page<Cafe> getAllCafesDynamicParam(
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @PageableDefault(page = 0, size = 20) Pageable pageable
    ) {
        return cafeService.getAllCafesWithNameOrAddress(name, address, pageable);
    }
}
