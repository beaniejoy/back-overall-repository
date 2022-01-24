package io.beaniejoy.springdatajpa.service;

import io.beaniejoy.springdatajpa.data.CafeParam;
import io.beaniejoy.springdatajpa.data.CafeSearch;
import io.beaniejoy.springdatajpa.dto.CafeResponse;
import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;

    private final CafeSearch cafeSearch;

    public Page<CafeResponse> getAllCafesWithNameOrAddress(String name, String address, Pageable pageable) {
        CafeParam cafeParam = CafeParam.builder()
                .name(name)
                .address(address)
                .build();

        Page<Cafe> result = cafeRepository.findAll(cafeSearch.toSpecification(cafeParam), pageable);
        List<CafeResponse> responses = result.getContent().stream()
                .map(CafeResponse::of)
                .collect(Collectors.toList());

        return new PageImpl<>(responses, pageable, result.getTotalElements());
    }
}
