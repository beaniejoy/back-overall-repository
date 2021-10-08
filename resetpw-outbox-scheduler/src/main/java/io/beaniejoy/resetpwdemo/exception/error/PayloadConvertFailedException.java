package io.beaniejoy.resetpwdemo.exception.error;

public class PayloadConvertFailedException extends RuntimeException{
    public PayloadConvertFailedException() {
        super("json 형태의 payload를 변환하는데 에러가 발생하였습니다.");
    }
}
