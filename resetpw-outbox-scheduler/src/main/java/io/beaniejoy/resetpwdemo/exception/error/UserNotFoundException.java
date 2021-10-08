package io.beaniejoy.resetpwdemo.exception.error;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("[" + email + "] User Not Found");
    }
}
