package io.beaniejoy.resetpwdemo.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {

    private String userName;

    private String email;

    private String password;

    private String address;

    private String phoneNumber;
}
