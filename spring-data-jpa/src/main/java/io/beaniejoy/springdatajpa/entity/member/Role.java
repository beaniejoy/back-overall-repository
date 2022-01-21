package io.beaniejoy.springdatajpa.entity.member;

public enum Role {
    ROLE_ADMIN("관리자"), ROLE_USER("사용자");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
