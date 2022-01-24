package io.beaniejoy.springdatajpa.controller;

import io.beaniejoy.springdatajpa.dto.CafeResponse;
import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.service.CafeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CafeController.class)
class CafeControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CafeService cafeService;

    List<Cafe> cafeList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        Cafe cafe1 = Cafe.builder()
                .id(1L)
                .name("test_cafe_1")
                .address("test_address_1")
                .phoneNumber("phone_1")
                .description("desc_1")
                .build();

        Cafe cafe2 = Cafe.builder()
                .id(1L)
                .name("test_cafe_2")
                .address("test_address_2")
                .phoneNumber("phone_2")
                .description("desc_2")
                .build();

        cafeList.add(cafe1);
        cafeList.add(cafe2);

    }

    @Test
    @DisplayName("Cafe 기본 전체조회 테스트")
    public void getAllCafesTest() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Cafe> result = new PageImpl<>(cafeList, pageable, 2);
        Page<CafeResponse> cafeResponses = toCafeResponseList(result, pageable);

        given(cafeService.getAllCafesWithNameOrAddress("", "address", pageable))
                .willReturn(cafeResponses);

        mvc.perform(get("/api/v1/cafes?name=&address=address")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private Page<CafeResponse> toCafeResponseList(Page<Cafe> entityList, Pageable pageable) {
        List<CafeResponse> responses = entityList.getContent().stream()
                .map(CafeResponse::of)
                .collect(Collectors.toList());

        return new PageImpl<>(responses, pageable, entityList.getTotalElements());
    }
}