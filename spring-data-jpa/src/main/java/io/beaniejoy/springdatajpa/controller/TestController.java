package io.beaniejoy.springdatajpa.controller;

import io.beaniejoy.springdatajpa.service.tx.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    private final ParentService testService;

    @PostMapping
    public String transactionTest() throws IOException {
        testService.justCallChildService();
        return "ok";
    }
}
