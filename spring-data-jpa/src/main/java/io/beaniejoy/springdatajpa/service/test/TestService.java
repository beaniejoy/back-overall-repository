package io.beaniejoy.springdatajpa.service.test;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
    private final TestNestedService test2Service;
    private final CafeRepository cafeRepository;

    @Transactional
    public void transactionTest() throws IOException {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        try {
            test2Service.nestedMethod();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        throw new IOException("test");
    }
}
