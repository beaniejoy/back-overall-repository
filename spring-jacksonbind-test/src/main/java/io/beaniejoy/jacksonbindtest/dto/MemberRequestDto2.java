package io.beaniejoy.jacksonbindtest.dto;


// 2. private field + getter 조합만으로 구성
public class MemberRequestDto2 {
    private Long id;
    private String name;
    private String address;
    private String email;

    // getter 가 없고 public 이 아닌 field 만 존재한다면 binding 이 되지 않는다.
    // "getXxxx()" -> "xxxx" 에 해당하는 맴버변수에 binding 됨
    // "getXxxx()" 로 설정되어 있으면 맴버변수도 "xxxx"로 통일해야 함 (안 그럼 매핑이 안됨)
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
        return "MemberRequestDto2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
