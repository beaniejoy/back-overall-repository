package io.beaniejoy.jacksonbindtest.dto.chap01_basic;

// 가장 기본적으로 ObjectMapper에는 default constructor가 필요하다.
// (ObjectMapper는 deserialize 할 때 default constructor를 가지고 객체를 생성)

// 1. public field만 존재
public class MemberRequestDto1 {
    // public 필드에 대해서는 binding 이 이루어진다.
    // private field 만으로 binding 이 일어나지 않는다.
    public Long id;
    public String name;
    public String address;
    public String email;

    @Override
    public String toString() {
        return "MemberRequestDto1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
