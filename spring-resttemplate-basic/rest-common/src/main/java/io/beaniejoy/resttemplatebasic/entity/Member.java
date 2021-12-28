package io.beaniejoy.resttemplatebasic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {
    @JsonProperty("hello_id")
    private Long id;

    @JsonProperty("hello_name")
    private String name;

    @JsonProperty("hello_email")
    private String email;

    @JsonProperty("hello_address")
    private String address;

    public Member() {
    }

    public Member(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
