package io.beaniejoy.resttemplatebasic.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExampleGetterDto {
    private Long id;

    private String name;

    private String email;

    private String address;

    private String helloName;

    public ExampleGetterDto() {
    }

//    public ExampleGetterDto(Long id, String name, String email, String address) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.address = address;
//    }

    public Long getId() {
        return id + 10L;
    }

    public String getHelloName() {
        return name;
    }

    public String getHelloEmail() {
        return email;
    }

    public String getHelloAddress() {
        return address;
    }

//    public void setHelloId(Long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }


    @Override
    public String toString() {
        return "ExampleGetterDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", helloName='" + helloName + '\'' +
                '}';
    }
}
