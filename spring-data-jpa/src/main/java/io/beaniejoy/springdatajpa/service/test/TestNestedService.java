package io.beaniejoy.springdatajpa.service.test;

import io.beaniejoy.springdatajpa.common.TestAnnotation;
import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestNestedService {
    private final CafeRepository cafeRepository;

    @Transactional
//    @TestAnnotation
    public void nestedMethod() throws IOException {
        Cafe cafe = Cafe.builder()
                .name("beanie's cafe")
                .description("beanie cafe desc")
                .phoneNumber("01023450981")
                .address("beanie cafe's address")
                .build();

//        try {
        cafeRepository.save(cafe);
        throw new IOException("test");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
    }
}
