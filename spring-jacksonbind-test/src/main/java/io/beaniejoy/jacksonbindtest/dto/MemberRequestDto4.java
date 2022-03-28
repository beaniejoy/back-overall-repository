package io.beaniejoy.jacksonbindtest.dto;

public class MemberRequestDto4 {
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

    // custom return value 에 대해서 response json 에 해당 내용으로 return

}
