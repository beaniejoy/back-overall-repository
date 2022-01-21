package io.beaniejoy.springdatajpa.entity.member;

import io.beaniejoy.springdatajpa.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String phoneNumber;

    private Role role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Review> reviews;
}
