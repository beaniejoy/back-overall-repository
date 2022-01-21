package io.beaniejoy.springdatajpa.entity.cafe;

import io.beaniejoy.springdatajpa.entity.BaseEntity;
import io.beaniejoy.springdatajpa.entity.review.Review;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cafe")
public class Cafe extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String description;

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY)
    private List<CafeMenu> cafeMenus;
}
