package io.beaniejoy.jacksonbindtest.dto.part01_basic;

// 6. 기본적인 POJO 구조에서 setter 에 임의의 값 주입하는 경우
public class MemberRequestDto6 {
    private Long id;
    private String name;
    private String address;
    private String email;

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

    public void setId(Long id) {
        this.id = id;
    }

    // 임의의 값을 setter에서 필드에 주입하는 경우
    // json 데이터에 name, address key로 들어오는 value는 무시되고
    // 임의로 지정한 값이 들어가게 된다.
    public void setName(String name) {
        this.name = "default name";
    }

    public void setAddress(String address) {
        this.address = "default address";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MemberRequestDto6{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
