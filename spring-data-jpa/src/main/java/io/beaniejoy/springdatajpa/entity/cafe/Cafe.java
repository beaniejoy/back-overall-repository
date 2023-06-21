package io.beaniejoy.springdatajpa.entity.cafe;

import io.beaniejoy.springdatajpa.entity.BaseEntity;
import io.beaniejoy.springdatajpa.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
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

    @Builder.Default
    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY)
    private List<CafeMenu> cafeMenus = new ArrayList<>();

    public void updateInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
