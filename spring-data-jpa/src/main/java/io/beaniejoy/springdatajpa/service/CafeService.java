package io.beaniejoy.springdatajpa.service;

import io.beaniejoy.springdatajpa.data.CafeSearch;
import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CafeService {
    private static final Logger logger = LoggerFactory.getLogger(CafeService.class);

    private final CafeRepository cafeRepository;

    public void getAllCafes() {
        CafeSearch cafeSearch = CafeSearch.builder()
                .name("test_cafe_1")
                .address("address")
                .build();

        List<Cafe> cafes = cafeRepository.findAll(cafeSearch.toSpecification());
        logger.info("cafe name: {}, address: {}", cafes.get(0).getName(), cafes.get(0).getAddress());
    }
}
