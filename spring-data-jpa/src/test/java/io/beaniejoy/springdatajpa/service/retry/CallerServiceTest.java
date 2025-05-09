package io.beaniejoy.springdatajpa.service.retry;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CallerServiceTest {
    @Autowired
    CallerService callerService;

    @Test
    @Disabled
    void retryTest() {
        callerService.callRetry();
    }
}