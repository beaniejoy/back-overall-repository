package io.beaniejoy.springdatajpa.service.pc;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.repository.CafeMenuRepository;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PCChildService {
    private final CafeRepository cafeRepository;
    private final CafeMenuRepository cafeMenuRepository;

    public void callChildMethodWithNoTx() {
        Cafe cafe = cafeRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("cafe not found"));
    }
}
