package io.beaniejoy.resetpwdemo.core.service;

import io.beaniejoy.resetpwdemo.core.domain.RoleType;
import io.beaniejoy.resetpwdemo.core.domain.User;
import io.beaniejoy.resetpwdemo.core.dto.request.UserRegistrationRequest;
import io.beaniejoy.resetpwdemo.core.dto.response.UserRegistrationResponse;
import io.beaniejoy.resetpwdemo.core.dto.response.UserSearchResponse;
import io.beaniejoy.resetpwdemo.core.repository.UserRepository;
import io.beaniejoy.resetpwdemo.exception.error.UserEmailExistedException;
import io.beaniejoy.resetpwdemo.security.JavaPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JavaPasswordEncoder javaPasswordEncoder;

    public UserRegistrationResponse registerUser(UserRegistrationRequest resource) {

        userRepository.findByEmail(resource.getEmail())
                .ifPresent(user -> {
                    throw new UserEmailExistedException(user.getEmail());
                });

        String salt = javaPasswordEncoder.generateSalt();
        String password = resource.getPassword();
        String encodedPassword = javaPasswordEncoder.encode(password, salt);

        User registeredUser = userRepository.save(
                toEntityForSignUp(resource, encodedPassword, salt));

        return toResponseRegistration(registeredUser);
    }

    private User toEntityForSignUp(UserRegistrationRequest resource,
                                  String encodedPassword,
                                  String salt) {
        return User.builder()
                .userName(resource.getUserName())
                .email(resource.getEmail())
                .password(encodedPassword)
                .address(resource.getAddress())
                .phoneNumber(resource.getPhoneNumber())
                .roleType(RoleType.ROLE_USER)   // default role type setting
                .salt(salt)
                .build();

    }

    private UserRegistrationResponse toResponseRegistration(User user) {
        return UserRegistrationResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }

    @Transactional(readOnly = true)
    public List<UserSearchResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponseSearch)
                .collect(Collectors.toList());
    }

    private UserSearchResponse toResponseSearch(User user) {
        return UserSearchResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .roleType(user.getRoleType())
                .build();
    }
}
