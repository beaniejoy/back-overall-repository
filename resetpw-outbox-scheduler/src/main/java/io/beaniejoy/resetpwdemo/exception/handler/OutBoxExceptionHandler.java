package io.beaniejoy.resetpwdemo.exception.handler;

import io.beaniejoy.resetpwdemo.exception.error.PayloadConvertFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OutBoxExceptionHandler {
    @ExceptionHandler(PayloadConvertFailedException.class)
    public ResponseEntity<String> handlePayloadConvertFailedException(PayloadConvertFailedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
