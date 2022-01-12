package io.beaniejoy.resttemplatebasic.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExampleGetter {

    private Long id;

    private String name;

    private String email;

    private String address;

    public ExampleGetter() {
    }

    public ExampleGetter(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getHelloId() {
        return id;
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
}
