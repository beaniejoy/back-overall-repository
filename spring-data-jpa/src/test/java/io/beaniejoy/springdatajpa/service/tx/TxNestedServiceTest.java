package io.beaniejoy.springdatajpa.service.tx;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import io.beaniejoy.springdatajpa.service.CafeService;
import io.beaniejoy.springdatajpa.service.tx.ChildService;
import io.beaniejoy.springdatajpa.service.tx.ParentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
class TxNestedServiceTest {
    @Autowired
    private ParentService parentService;
    @Autowired
    private ChildService childService;
    @Autowired
    private CafeRepository cafeRepository;
    @Autowired
    private CafeService cafeService;

    @BeforeEach
    public void setup() {
        Cafe cafe1 = Cafe.builder()
                .id(100L)
                .name("test_cafe_1")
                .address("test_address_1")
                .description("desc1")
                .phoneNumber("phone_number_1")
                .build();

        Cafe cafe2 = Cafe.builder()
                .id(200L)
                .name("test_cafe_2")
                .address("test_address_2")
                .description("desc2")
                .phoneNumber("phone_number_2")
                .build();

        Cafe cafe3 = Cafe.builder()
                .id(300L)
                .name("test_cafe_3")
                .address("test_address_3")
                .description("desc3")
                .phoneNumber("phone_number_3")
                .build();

        cafeRepository.save(cafe1);
        cafeRepository.save(cafe2);
        cafeRepository.save(cafe3);
    }

    @AfterEach
    public void afterEach() {
        cafeService.truncateCafe();
    }

    @Test
    @DisplayName("CASE 1 > parent: REQUIRED / child: REQUIRED 상황에서 롤백 테스트")
    public void just_call_with_tx_all_REQUIRED_test() {
        assertThrows(RuntimeException.class, () -> {
            parentService.justCallChildService();
        });

        List<Cafe> cafes = cafeService.getAllCafes();

        // 결과: 둘 다 롤백
        assertEquals(cafes.size(), 3);

        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("joy's cafe")), "No joy's cafe");
        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("beanie's cafe")), "No beanie's cafe");
    }

    /**
     * parentMethod에서 try catch Exception 처리를 해도
     * 하나의 Tx 안에서 RuntimeException 발생했기에 **롤백 예외** 발생하면서 전부 롤백
     * (UnexpectedRollbackException)
     */
    @Test
    @DisplayName("CASE 2 > parent: REQUIRED, try ~ catch / child: REQUIRED 상황에서 롤백 테스트")
    public void parent_try_catch_with_tx_all_REQUIRED_test() {
        // Transaction silently rolled back because it has been marked as rollback-only
        assertThrows(UnexpectedRollbackException.class, () -> {
            parentService.callChildServiceTryCatchCase1();
        });

        List<Cafe> cafes = cafeService.getAllCafes();

        // 결과: 둘 다 롤백
        assertEquals(cafes.size(), 3);

        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("joy's cafe")), "No joy's cafe");
        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("beanie's cafe")), "No beanie's cafe");
    }

    /**
     * parentMethod에서 try ~ catch 하든 안하든 상관없이 둘 다 커밋됨
     */
    @Test
    @DisplayName("CASE 3 > parent: REQUIRED / child: REQUIRED, try ~ catch 상황에서 커밋 테스트")
    public void child_try_catch_with_tx_all_REQUIRED_test() {
        parentService.callChildServiceTryCatchCase2();

        List<Cafe> cafes = cafeService.getAllCafes();

        // 결과: 둘 다 커밋
        assertEquals(cafes.size(), 5);

        assertTrue(cafes.stream().anyMatch(res -> res.getName().equals("joy's cafe")), "No joy's cafe");
        assertTrue(cafes.stream().anyMatch(res -> res.getName().equals("beanie's cafe")), "No beanie's cafe");
    }

    /**
     * parentMethod에서 try ~ catch 처리 X
     * RuntimeException이 전파되어 모두 롤백 처리
     */
    @Test
    @DisplayName("CASE 4 > parent: REQUIRED / child: REQUIRES_NEW 상황에서 커밋 테스트")
    public void just_call_with_tx_parent_REQUIRED_child_REQUIRES_NEW_test() {
        assertThrows(RuntimeException.class, () -> {
            parentService.callChildServiceWithNewTx();
        });

        List<Cafe> cafes = cafeService.getAllCafes();

        // 결과: 둘 다 커밋
        assertEquals(cafes.size(), 3);

        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("joy's cafe")), "No joy's cafe");
        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("beanie's cafe")), "No beanie's cafe");
    }

    @Test
    @DisplayName("CASE 5 > parent: REQUIRED, try ~ catch / child: REQUIRES_NEW 상황에서 커밋 테스트")
    public void parent_try_catch_with_tx_parent_REQUIRED_child_REQUIRES_NEW_test() {
        parentService.callChildServiceTryCatchWithNewTx();

        List<Cafe> cafes = cafeService.getAllCafes();

        // 결과: parent commit, child rollback
        assertEquals(cafes.size(), 4);

        assertTrue(cafes.stream().anyMatch(res -> res.getName().equals("joy's cafe")), "No joy's cafe");
        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("beanie's cafe")), "No beanie's cafe");
    }

    @Test
    @DisplayName("CASE 6 > parent: REQUIRED, checked exception  / child: REQUIRED 상황에서 커밋 테스트")
    public void parent_throw_checked_with_tx_all_REQUIRED_test() {
        assertThrows(IOException.class, () -> {
            parentService.callChildServiceThrowChecked();
        });

        List<Cafe> cafes = cafeService.getAllCafes();

        // 결과: 둘 다 커밋
        assertEquals(cafes.size(), 5);
        assertTrue(cafes.stream().anyMatch(res -> res.getName().equals("joy's cafe")), "No joy's cafe");
        assertTrue(cafes.stream().anyMatch(res -> res.getName().equals("beanie's cafe")), "No beanie's cafe");
    }

    @Test
    @DisplayName("CASE 7 > parent: REQUIRED / child: REQUIRED, checked exception 상황에서 커밋 테스트")
    public void child_throw_checked_with_tx_all_REQUIRED_test() {
        assertThrows(IOException.class, () -> {
            parentService.callChildServiceWithThrowCheckedCase1();
        });

        List<Cafe> cafes = cafeService.getAllCafes();

        // 결과: 둘 다 커밋
        assertEquals(cafes.size(), 5);
        assertTrue(cafes.stream().anyMatch(res -> res.getName().equals("joy's cafe")), "No joy's cafe");
        assertTrue(cafes.stream().anyMatch(res -> res.getName().equals("beanie's cafe")), "No beanie's cafe");
    }

    @Test
    @DisplayName("CASE 8 > parent: REQUIRED / child: REQUIRES_NEW, checked exception 상황에서 커밋 테스트")
    public void child_throw_checked_with_tx_parent_REQUIRED_child_REQUIRES_NEW_test() {
        parentService.callChildServiceWithThrowCheckedCase2();

        List<Cafe> cafes = cafeService.getAllCafes();

        // 결과: 둘 다 커밋
        assertEquals(cafes.size(), 5);
        assertTrue(cafes.stream().anyMatch(res -> res.getName().equals("joy's cafe")), "No joy's cafe");
        assertTrue(cafes.stream().anyMatch(res -> res.getName().equals("beanie's cafe")), "No beanie's cafe");
    }


    @Test
    @DisplayName("CASE 9 > parent: REQUIRED / child: REQUIRED, custom aspect runtime exception 상황에서 롤백 테스트")
    public void parent_try_catch_with_tx_all_REQUIRED_and_custom_aspect_unchecked_exception_test() {
        // rollback exception
//        assertThrows(RuntimeException.class, () -> {
            parentService.callChildServiceWithCustomAspectCase1();
//        });

        List<Cafe> cafes = cafeService.getAllCafes();

        // 결과: 둘 다 롤백
        assertEquals(3, cafes.size());
        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("joy's cafe")), "No joy's cafe");
        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("beanie's cafe")), "No beanie's cafe");
    }

    @Test
    @DisplayName("CASE 10 > parent: REQUIRED / child: REQUIRED, custom aspect checked exception 상황에서 롤백 테스트")
    public void parent_try_catch_with_tx_all_REQUIRED_and_custom_aspect_checked_exception_test() {
        // rollback exception
        assertThrows(UnexpectedRollbackException.class, () -> {
            parentService.callChildServiceWithCustomAspectCase2();
        });

        List<Cafe> cafes = cafeService.getAllCafes();

        // 결과: 둘 다 롤백
        assertEquals(3, cafes.size());
        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("joy's cafe")), "No joy's cafe");
        assertFalse(cafes.stream().anyMatch(res -> res.getName().equals("beanie's cafe")), "No beanie's cafe");
    }

    @Test
    @DisplayName("CASE 11 > child: REQUIRED, custom aspect exception 상황에서 롤백 테스트")
    public void child_with_tx_REQUIRED_and_custom_aspect_exception_test() {
        // 둘 다 RuntimeException 으로 떨어짐
        assertThrows(RuntimeException.class, () -> {
            childService.saveWithCustomAspectThrowRuntimeException();
        });

        // checked exception 에도 롤백이 된다.
        assertThrows(UndeclaredThrowableException.class, () -> {
            childService.saveWithCustomAspectThrowCheckedException();
        });
    }
}