package io.beaniejoy.resetpwdemo.api;

import io.beaniejoy.resetpwdemo.core.dto.request.UserEmailRequest;
import io.beaniejoy.resetpwdemo.core.dto.request.UserRegistrationRequest;
import io.beaniejoy.resetpwdemo.core.dto.response.UserInfo;
import io.beaniejoy.resetpwdemo.core.dto.response.UserRegistrationResponse;
import io.beaniejoy.resetpwdemo.core.dto.response.UserSearchResponse;
import io.beaniejoy.resetpwdemo.core.service.AuthService;
import io.beaniejoy.resetpwdemo.core.service.UserPasswordResetService;
import io.beaniejoy.resetpwdemo.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final AuthService authService;

    private final UserPasswordResetService userPasswordResetService;
    // 회원가입 API
    @PostMapping("/user/signup")
    public ResponseEntity<UserRegistrationResponse> signUp(@RequestBody UserRegistrationRequest resource) throws URISyntaxException {
        UserRegistrationResponse response = userService.registerUser(resource);

        URI uri = new URI("/user/" + response.getId());
        return ResponseEntity.created(uri).body(response);
    }

    // 비밀번호 초기화 작업을 위한 이메일 발송 API
    @PostMapping("/find-password")
    public ResponseEntity<String> findPassword(@RequestBody UserEmailRequest resource) {
        // 입력받은 email 주소가 실제 존재하는 사용자인지 조회
        UserInfo userInfoDto = authService.findByEmail(resource.getEmail());
        // password 초기화를 위한 Key 발급 및 테이블 저장
        userPasswordResetService.saveResetKey(userInfoDto.getUserEmail(), userInfoDto.getUserName());

        return ResponseEntity.ok("비밀번호 초기화를 위한 이메일 전송이 완료되었습니다.");
    }

    @GetMapping("/user")
    public List<UserSearchResponse> list() {
        return userService.findAllUsers();
    }
}
