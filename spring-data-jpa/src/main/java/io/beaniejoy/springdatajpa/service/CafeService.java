package io.beaniejoy.springdatajpa.service;

import io.beaniejoy.springdatajpa.data.CafeSearch;
import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CafeService {
    private static final Logger logger = LoggerFactory.getLogger(CafeService.class);

    private final CafeRepository cafeRepository;

    public Page<Cafe> getAllCafesWithNameOrAddress(String name, String address, Pageable pageable) {
        CafeSearch cafeSearch = CafeSearch.builder()
                .name(name)
                .address(address)
                .build();

        return cafeRepository.findAll(cafeSearch.toSpecification(), pageable);
    }
}
