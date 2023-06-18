package io.beaniejoy.springdatajpa.service.tx;

import io.beaniejoy.springdatajpa.common.CustomAnnotation;
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
public class ChildService {
    private final CafeRepository cafeRepository;

    @Transactional
    public void save() {
        Cafe cafe = Cafe.builder()
                .name("beanie's cafe")
                .description("beanie cafe desc")
                .phoneNumber("01023450981")
                .address("beanie cafe's address")
                .build();

        cafeRepository.save(cafe);
    }

    @Transactional
    public void saveAndThrowRuntimeException() {
        Cafe cafe = Cafe.builder()
                .name("beanie's cafe")
                .description("beanie cafe desc")
                .phoneNumber("01023450981")
                .address("beanie cafe's address")
                .build();

        cafeRepository.save(cafe);
        throw new RuntimeException("childMethodThrowRuntimeException");
    }

    @Transactional
    public void saveAndThrowRuntimeExceptionWithTryCatch() {
        Cafe cafe = Cafe.builder()
                .name("beanie's cafe")
                .description("beanie cafe desc")
                .phoneNumber("01023450981")
                .address("beanie cafe's address")
                .build();

        cafeRepository.save(cafe);

        try {
            throw new RuntimeException("childMethodThrowRuntimeException");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAndThrowRuntimeExceptionWithNewTx() {
        Cafe cafe = Cafe.builder()
                .name("beanie's cafe")
                .description("beanie cafe desc")
                .phoneNumber("01023450981")
                .address("beanie cafe's address")
                .build();

        cafeRepository.save(cafe);
        throw new RuntimeException("test");
    }

    @Transactional
    public void saveThrowIOException() throws IOException {
        Cafe cafe = Cafe.builder()
                .name("beanie's cafe")
                .description("beanie cafe desc")
                .phoneNumber("01023450981")
                .address("beanie cafe's address")
                .build();

        cafeRepository.save(cafe);
        throw new IOException("test");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveThrowIOExceptionWithNewTx() throws IOException {
        Cafe cafe = Cafe.builder()
                .name("beanie's cafe")
                .description("beanie cafe desc")
                .phoneNumber("01023450981")
                .address("beanie cafe's address")
                .build();

        cafeRepository.save(cafe);
        throw new IOException("test");
    }

    @Transactional
    @CustomAnnotation
    public void saveWithCustomAspectThrowRuntimeException() throws IOException {
        Cafe cafe = Cafe.builder()
                .name("beanie's cafe")
                .description("beanie cafe desc")
                .phoneNumber("01023450981")
                .address("beanie cafe's address")
                .build();

        cafeRepository.save(cafe);
    }
}
