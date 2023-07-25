package io.beaniejoy.springdatajpa.service.retry;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RetryService {
    private final CafeRepository cafeRepository;

    public RetryService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @Retryable(retryFor = RuntimeException.class, maxAttempts = 2)
    public void retry() {
        log.info("retry!!");

        cafeRepository.save(Cafe.builder()
                .name("test cafe")
                .address("test address")
                .phoneNumber("0107320431")
                .description("desc")
                .build()
        );

        throw new RuntimeException("retry Test");
    }
}
