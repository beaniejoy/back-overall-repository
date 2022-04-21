package io.beaniejoy.jacksonbindtest.dto.chap02_constructor;

// 11. 인자가 두 개 이상인 상황
public class MemberConstructorDto4 {
    private String name;
    private String address;

    public MemberConstructorDto4(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "MemberConstructorDto3{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
