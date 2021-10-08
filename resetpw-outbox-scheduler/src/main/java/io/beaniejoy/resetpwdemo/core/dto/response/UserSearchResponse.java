package io.beaniejoy.resetpwdemo.core.dto.response;

import io.beaniejoy.resetpwdemo.core.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchResponse {
    private Long id;

    private String userName;

    private String email;

    private String address;

    private String phoneNumber;

    private RoleType roleType;
}
