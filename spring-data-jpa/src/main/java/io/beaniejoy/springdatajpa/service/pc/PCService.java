package io.beaniejoy.springdatajpa.service.pc;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.entity.cafe.CafeMenu;
import io.beaniejoy.springdatajpa.repository.CafeMenuRepository;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PCService {
    private final CafeRepository cafeRepository;
    private final CafeMenuRepository cafeMenuRepository;
    private final PCChildService pcChildService;

    @Transactional
    public void applyPCCache() {
        log.info("메소드 시작");
//        Cafe cafe = cafeRepository.findById(2L)
//                .orElseThrow(() -> new RuntimeException("cafe not found"));

        pcChildService.callChildMethodWithNoTx();
        CafeMenu cafeMenu = cafeMenuRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("cafeMenu not found"));

        log.info("메소드 진행중 2");
        Cafe cafe2 = cafeMenu.getCafe();
        log.info("조회 결과: {}", cafe2.getName());


//        log.info(String.valueOf(cafe.getId()));
//        log.info(cafe.getName());
//        log.info(String.valueOf(cafe2.getId()));
    }
}
