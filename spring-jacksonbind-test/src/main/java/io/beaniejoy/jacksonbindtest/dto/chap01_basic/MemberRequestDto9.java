package io.beaniejoy.jacksonbindtest.dto.chap01_basic;

import com.fasterxml.jackson.annotation.JsonCreator;

// 9. 인자가 한 개인 생성자만 존재하는 경우(single argument constructor)
public class MemberRequestDto9 {
    private String name;

    @JsonCreator
    public MemberRequestDto9(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MemberRequestDto9{" +
                "name='" + name + '\'' +
                '}';
    }
}
