package io.beaniejoy.jacksonbindtest.dto.part02_constructor;

import com.fasterxml.jackson.annotation.JsonCreator;

// 9. 인자가 한 개인 생성자만 존재하는 경우(single argument constructor)
public class MemberConstructorDto2 {
    private String name;

    @JsonCreator
    public MemberConstructorDto2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MemberRequestDto9{" +
                "name='" + name + '\'' +
                '}';
    }
}
