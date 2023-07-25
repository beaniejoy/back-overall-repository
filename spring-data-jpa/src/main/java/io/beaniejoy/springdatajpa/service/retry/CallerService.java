package io.beaniejoy.springdatajpa.service.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CallerService {
    private final RetryService retryService;

    public CallerService(RetryService retryService) {
        this.retryService = retryService;
    }

    @Transactional
    public void callRetry() {
        log.info("caller service before retry");
        retryService.retry();
    }
}
