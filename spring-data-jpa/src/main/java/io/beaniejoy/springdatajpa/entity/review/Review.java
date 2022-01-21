package io.beaniejoy.springdatajpa.entity.review;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;
}