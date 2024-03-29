package io.beaniejoy.springdatajpa.dto;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CafeResponse {
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String description;

    public static CafeResponse of(Cafe cafe) {
        return CafeResponse.builder()
                .id(cafe.getId())
                .name(cafe.getName())
                .address(cafe.getAddress())
                .phoneNumber(cafe.getPhoneNumber())
                .description(cafe.getDescription())
                .build();
    }
}
