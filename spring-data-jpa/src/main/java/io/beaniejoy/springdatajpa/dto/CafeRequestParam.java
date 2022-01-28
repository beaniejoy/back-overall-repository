package io.beaniejoy.springdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CafeRequestParam {
    private String name;
    private String address;
    private String phoneNumber;
}
