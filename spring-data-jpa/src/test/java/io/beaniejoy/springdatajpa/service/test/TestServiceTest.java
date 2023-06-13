package io.beaniejoy.springdatajpa.service.test;

import io.beaniejoy.springdatajpa.dto.CafeRequestParam;
import io.beaniejoy.springdatajpa.dto.CafeResponse;
import io.beaniejoy.springdatajpa.service.CafeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestServiceTest {
    @Autowired
    private TestService testService;
    @Autowired
    private TestNestedService testNestedService;

    @Autowired
    private CafeService cafeService;

    @Test
    public void transactionTest() {
//        assertThrows(RuntimeException.class, () -> {
//            testService.transactionTest();
//        });

        testService.transactionTest();

        Page<CafeResponse> response =
                cafeService.getAllCafesWithParams(new CafeRequestParam(), Pageable.unpaged());

        System.out.println(response.getTotalElements());
        assertTrue(
                response.getContent().stream().anyMatch(res -> res.getName().equals("joy's cafe")),
                "No joy's cafe"
        );
        assertTrue(
                response.getContent().stream().anyMatch(res -> res.getName().equals("beanie's cafe")),
                "No beanie's cafe"
        );
        assertEquals(response.getTotalElements(), 5, "Invalid Total Count");
    }
}