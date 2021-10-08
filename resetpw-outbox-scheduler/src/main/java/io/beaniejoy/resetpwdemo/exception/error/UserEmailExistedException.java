package io.beaniejoy.resetpwdemo.exception.error;

public class UserEmailExistedException extends RuntimeException {
    public UserEmailExistedException(String email) {
        super("[" + email + "] 계정은 이미 가입된 상태입니다.");
    }
}
