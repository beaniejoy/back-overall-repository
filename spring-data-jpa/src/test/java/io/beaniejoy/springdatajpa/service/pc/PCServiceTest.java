package io.beaniejoy.springdatajpa.service.pc;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.entity.cafe.CafeMenu;
import io.beaniejoy.springdatajpa.repository.CafeMenuRepository;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
class PCServiceTest {
    @Autowired
    CafeRepository cafeRepository;
    @Autowired
    CafeMenuRepository cafeMenuRepository;

    @Autowired
    PCService pcService;

    @BeforeEach
    @Transactional
    public void setup() {
        System.out.println("카페 구성");
        Cafe cafe = Cafe.builder()
                .name("test_cafe_1")
                .address("test_address_1")
                .description("desc1")
                .phoneNumber("phone_number_1")
                .build();

        Cafe cafe2 = Cafe.builder()
                .name("test_cafe_2")
                .address("test_address_2")
                .description("desc2")
                .phoneNumber("phone_number_2")
                .build();

        Cafe savedCafe = cafeRepository.save(cafe);
        Cafe savedCafe2 = cafeRepository.save(cafe2);

        System.out.println("카페메뉴 구성" + savedCafe.getId());
        CafeMenu americano = CafeMenu.builder()
                .name("아메리카노")
                .price(new BigDecimal("2000"))
                .build();

        CafeMenu latte = CafeMenu.builder()
                .name("라떼")
                .price(new BigDecimal("3000"))
                .build();

        americano.updateCafe(savedCafe);
        latte.updateCafe(savedCafe);

        CafeMenu savedCafeMenu = cafeMenuRepository.save(americano);
        System.out.println("카페메뉴 구성 완료" + savedCafeMenu.getId());
        cafeMenuRepository.save(latte);
    }

    @Test
    void pcTest() {
        pcService.applyPCCache();
    }
}