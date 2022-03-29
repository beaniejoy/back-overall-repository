package io.beaniejoy.jacksonbindtest.dto;

// 3. field name과 getter name이 불일치 하는 경우
public class MemberRequestDto3 {
    private Long id;
    // getName 메소드가 없어서 "name" key 에 대해서도 binding 실패
    private String name;
    // getName 메소드가 없어서 "address" key 에 대해서도 binding 실패
    private String address;
    private String email;

    public Long getId() {
        return id;
    }

    // helloName field 존재하지 않아 binding 실패
    // controller 에서 response 내보낼 때 helloName key 로 내보내진다.
    // {"helloName":null}
    // 기존 "name" key 이름으로 response 에 아예 포함되지 않는다.
    public String getHelloName() {
        return name;
    }

    // helloAddress field 존재하지 않아 binding 실패
    // {"helloAddress":null}
    public String getHelloAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "MemberRequestDto3{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
