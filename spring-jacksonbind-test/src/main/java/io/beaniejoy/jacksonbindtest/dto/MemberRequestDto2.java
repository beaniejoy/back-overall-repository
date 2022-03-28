package io.beaniejoy.jacksonbindtest.dto;

/*
* private field + getter
*/
public class MemberRequestDto2 {
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
