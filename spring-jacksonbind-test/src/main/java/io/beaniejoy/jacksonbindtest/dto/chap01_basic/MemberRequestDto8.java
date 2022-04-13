package io.beaniejoy.jacksonbindtest.dto.chap01_basic;

// 8. 기본 생성자만 없는 경우
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

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "MemberRequestDto8{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
