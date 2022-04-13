package io.beaniejoy.jacksonbindtest.dto.chap01_basic;

// 5. private field & setter 만으로 구성
public class MemberRequestDto5 {
    private Long id;
    private String name;
    private String address;
    private String email;

    // getter 없이 setter만으로도 ObjectMapper binding 된다.
    // 다만 api response json data에는 getter가 없기에 아무런 데이터를 반환하지 못한다.
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MemberRequestDto5{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
