package io.beaniejoy.springdatajpa.controller;

import io.beaniejoy.springdatajpa.service.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    private final TestService testService;

    @PostMapping
    public String transactionTest() throws IOException {
        testService.transactionTest();
        return "ok";
    }
}
