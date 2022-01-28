package io.beaniejoy.springdatajpa.service;

import io.beaniejoy.springdatajpa.dto.CafeRequestParam;
import io.beaniejoy.springdatajpa.dto.CafeResponse;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class CafeServiceTest {
    @Autowired
    CafeService cafeService;

    @Autowired
    CafeRepository cafeRepository;

    @Test
    @DisplayName("(Spec)기본적인 Spec 조건으로 Cafe 전체 조회 테스트")
    public void findAllTestWithSpecification() {
        Pageable pageable = PageRequest.of(0, 20);

        CafeRequestParam requestParam = new CafeRequestParam("test_cafe_3", "address", "phone_number_3");
        Page<CafeResponse> result = cafeService.getAllCafesWithParams(requestParam, pageable);
        List<CafeResponse> content = result.getContent();

        assertEquals(result.getTotalElements(), 1);
        assertEquals(content.get(0).getId(), 300L);
        assertEquals(content.get(0).getName(), "test_cafe_3");
        assertEquals(content.get(0).getAddress(), "test_address_3");
        assertEquals(content.get(0).getPhoneNumber(), "phone_number_3");
    }

    @Test
    @DisplayName("(Spec)없는 검색 조건으로 조회시 No Cafe List 반환 테스트")
    public void returnNullWhenQueryWithNotExistingParamsWithSpecification() {
        Pageable pageable = PageRequest.of(0, 20);

        CafeRequestParam addressNotExistedParam = new CafeRequestParam(null, "not_valid", null);
        CafeRequestParam nameNotExistedParam = new CafeRequestParam("not_valid", null, null);

        Page<CafeResponse> resultWithAddress = cafeService.getAllCafesWithParams(addressNotExistedParam, pageable);
        Page<CafeResponse> resultWithName = cafeService.getAllCafesWithParams(nameNotExistedParam, pageable);

        assertTrue(resultWithAddress.isEmpty());
        assertTrue(resultWithName.isEmpty());
    }
}