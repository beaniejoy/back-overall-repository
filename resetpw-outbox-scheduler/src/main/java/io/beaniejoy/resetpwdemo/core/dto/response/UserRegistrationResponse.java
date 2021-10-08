package io.beaniejoy.resetpwdemo.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse {

    private Long id;

    private String userName;

    private String email;
}
