package io.beaniejoy.springdatajpabook.entity;

import javax.persistence.*;

@Entity
@NamedQuery(
        name = "Member.findAllByName",
        query = "select m from Member m where m.name = :name and m.email like '%@example.com'")
@Table(name = "member")
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    private String email;

    public Member() {
    }

    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
}
