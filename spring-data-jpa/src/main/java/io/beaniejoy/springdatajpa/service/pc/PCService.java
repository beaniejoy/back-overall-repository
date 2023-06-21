package io.beaniejoy.springdatajpa.service.pc;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PCService {
    private final CafeRepository cafeRepository;

    public void applyPCCache() {
        List<Cafe> cafes = cafeRepository.findAll();

        Cafe cafe = cafes.stream().findFirst().orElseThrow(() -> new RuntimeException("not found"));
        Cafe cafe2 = cafeRepository.findById(cafe.getId())
                .orElseThrow(() -> new RuntimeException("cafe not found"));

        System.out.println(cafe2.getName());
    }
}
