package io.beaniejoy.jacksonbindtest.dto.part02_constructor;

// 12. 인자가 있는 생성자 + setter
public class MemberConstructorDto5 {
    private String name;
    private String address;

    private String email;

    public MemberConstructorDto5(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MemberConstructorDto3{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
