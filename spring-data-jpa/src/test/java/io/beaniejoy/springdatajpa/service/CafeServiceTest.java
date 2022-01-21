package io.beaniejoy.springdatajpa.service;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CafeServiceTest {
    @Autowired
    CafeService cafeService;

    @Autowired
    CafeRepository cafeRepository;

    @BeforeEach
    public void setup() {
        List<Cafe> cafeList = new ArrayList<>();
        cafeList.add(Cafe.builder()
                .name("test_cafe_1")
                .address("test_address_1")
                .phoneNumber("phone_number_1")
                .build());

        cafeList.add(Cafe.builder()
                .name("test_cafe_2")
                .address("test_address_2")
                .phoneNumber("phone_number_2")
                .build());

        cafeRepository.saveAll(cafeList);
    }

    @AfterEach
    public void delete() {
        cafeRepository.deleteAll();
    }

    @Test
    public void test() {
        cafeService.getAllCafes();
    }
}