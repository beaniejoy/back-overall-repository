package io.beaniejoy.jacksonbindtest.dto.chap01_basic;

// 4. getter에서 해당 field를 return하지 않고 custom한 내용 반환하는 경우
public class MemberRequestDto4 {
    private Long id;
    private String name;
    private String address;
    private String email;

    public Long getId() {
        return id;
    }

    // custom return value 에 대해서 response json 에 해당 내용으로 return
    public String getName() {
        return "getName(): Custom return value";
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "MemberRequestDto4{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
