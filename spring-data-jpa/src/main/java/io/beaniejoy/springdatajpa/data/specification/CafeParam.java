package io.beaniejoy.springdatajpa.data.specification;

import io.beaniejoy.springdatajpa.dto.CafeRequestParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CafeParam {
    private String name;
    private String address;
    private String phoneNumber;

    public static CafeParam of(CafeRequestParam requestParam) {
        return new CafeParam(requestParam.getName(),
                requestParam.getAddress(),
                requestParam.getPhoneNumber()
        );
    }
}
