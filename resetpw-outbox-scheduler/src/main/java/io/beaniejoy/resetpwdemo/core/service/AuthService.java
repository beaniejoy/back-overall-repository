package io.beaniejoy.resetpwdemo.core.service;

import io.beaniejoy.resetpwdemo.core.domain.User;
import io.beaniejoy.resetpwdemo.core.repository.UserRepository;
import io.beaniejoy.resetpwdemo.core.dto.response.UserInfo;
import io.beaniejoy.resetpwdemo.exception.error.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserInfo findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        return toUserInfoDto(user);
    }

    private UserInfo toUserInfoDto(User user) {
        return UserInfo.builder()
                .userEmail(user.getEmail())
                .userName(user.getUserName())
                .build();
    }
}
