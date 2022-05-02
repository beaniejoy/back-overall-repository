package io.beaniejoy.jacksonbindtest.dto.part02_constructor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// 10. 인자가 한 개인 생성자만 존재하는 경우(@JsonCreator, @JsonProperty 사용하는 경우)
public class MemberConstructorDto3 {
    private String name;

    @JsonCreator
    public MemberConstructorDto3(@JsonProperty("name") String name) {
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
