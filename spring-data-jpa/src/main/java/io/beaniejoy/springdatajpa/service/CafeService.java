package io.beaniejoy.springdatajpa.service;

import io.beaniejoy.springdatajpa.data.specification.CafeParam;
import io.beaniejoy.springdatajpa.data.specification.CafeSearch;
import io.beaniejoy.springdatajpa.dto.CafeRequestParam;
import io.beaniejoy.springdatajpa.dto.CafeResponse;
import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.entity.cafe.QCafe;
import io.beaniejoy.springdatajpa.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;

    private final CafeSearch cafeSearch;

    public Page<CafeResponse> getAllCafesWithParams(CafeRequestParam requestParam, Pageable pageable) {
        Specification<Cafe> searchCafeSpecs = cafeSearch.toSpecification(CafeParam.of(requestParam));

        Page<Cafe> result = cafeRepository.findAll(searchCafeSpecs, pageable);
        List<CafeResponse> responses = toResponseList(result.getContent());

        return new PageImpl<>(responses, pageable, result.getTotalElements());
    }

    public Page<CafeResponse> getAllCafesWithQuerydsl(CafeRequestParam requestParam, Pageable pageable) {
        QCafe cafe = QCafe.cafe;

        Page<Cafe> result = cafeRepository.findAll(
                cafe.name.eq(requestParam.getName())
                        .and(cafe.address.contains(requestParam.getAddress()))
                        .and(cafe.phoneNumber.eq(requestParam.getPhoneNumber())),
                pageable
        );

        List<CafeResponse> responses = toResponseList(result.getContent());

        return new PageImpl<>(responses, pageable, result.getTotalElements());
    }

    private List<CafeResponse> toResponseList(List<Cafe> cafeList) {
        return cafeList.stream()
                .map(CafeResponse::of)
                .collect(Collectors.toList());
    }

    public void postCafe() {
        cafeRepository.save(Cafe.builder()
                .name("hello")
                .description("good")
                .phoneNumber("01010101")
                .address("address_good")
                .build());
    }

    @Transactional
    public void updateTest() {
        // cafe 조회 (id: 100L)
//        Cafe cafe = cafeRepository.findById(100L)
//                .orElseThrow(() -> new RuntimeException("Not Found Cafe"));

        // Detached Entity update test
        Cafe cafe = Cafe.builder()
                .id(100L)
                .name("update cafe name")
                .address("update cafe address")
                .phoneNumber("update cafe phone number")
                .description("update desc")
                .build();

        cafe.setUpdatedBy("beanie");
        cafe.setCreatedBy("beanie_first");
        // update cafe name & address
//        cafe.updateInfo("updated Cafe Name", "updated cafe address");

        cafeRepository.save(cafe);

        log.info("cafe name {}, address {}", cafe.getName(), cafe.getAddress());
        log.info("cafe updatedAt: {}", cafe.getUpdatedAt()); // 이전 데이터 로깅??
    }
}
