package io.beaniejoy.jacksonbindtest.dto.part01_basic;

// 7. setter의 이름이 필드명과 일치하지 않는 경우
public class MemberRequestDto8 {
    private Long id;
    private String name;
    private String address;
    private String email;

    public MemberRequestDto8(Long id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHelloAddress() {
        return "hello " + address;
    }

    public String getHelloEmail() {
        return "hello " + email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHelloName(String name) {
        this.name = name;
    }

    public void setHelloAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MemberRequestDto7{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
