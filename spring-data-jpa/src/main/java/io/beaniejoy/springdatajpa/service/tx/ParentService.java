package io.beaniejoy.springdatajpa.service.tx;

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
public class ParentService {
    private final ChildService childService;
    private final CafeRepository cafeRepository;

    // result: 둘 다 롤백
    @Transactional
    public void justCallChildService() {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        childService.saveAndThrowRuntimeException();
    }

    // result: 둘 다 롤백
    @Transactional
    public void callChildServiceTryCatchCase1() {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        try {
            childService.saveAndThrowRuntimeException();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    // result: 둘 다 커밋
    @Transactional
    public void callChildServiceTryCatchCase2() {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        childService.saveAndThrowRuntimeExceptionWithTryCatch();
    }

    // result: 둘 다 롤백(예외 전파)
    @Transactional
    public void callChildServiceWithNewTx() {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        childService.saveAndThrowRuntimeExceptionWithNewTx();
    }

    // result: parent commit, child rollback
    @Transactional
    public void callChildServiceTryCatchWithNewTx() {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        try {
            childService.saveAndThrowRuntimeExceptionWithNewTx();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    // result: 둘 다 commit
    @Transactional
    public void callChildServiceThrowChecked() throws IOException {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        childService.save();

        throw new IOException("test");
    }

    // result: 둘 다 commit
    @Transactional
    public void callChildServiceWithThrowCheckedCase1() throws IOException {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        childService.saveThrowIOException();
    }

    // result: 둘 다 commit
    @Transactional
    public void callChildServiceWithThrowCheckedCase2() {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        try {
            childService.saveThrowIOExceptionWithNewTx();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Transactional
    public void callChildServiceWithCustomAspectCase1() {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        try {
            childService.saveWithCustomAspectThrowRuntimeException();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Transactional
    public void callChildServiceWithCustomAspectCase2() {
        Cafe cafe = Cafe.builder()
                .name("joy's cafe")
                .description("joy cafe desc")
                .phoneNumber("01033334444")
                .address("joy cafe's address")
                .build();

        cafeRepository.save(cafe);

        try {
            childService.saveWithCustomAspectThrowRuntimeException();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
