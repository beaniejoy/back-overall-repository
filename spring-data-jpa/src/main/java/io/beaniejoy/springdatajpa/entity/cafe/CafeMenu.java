package io.beaniejoy.springdatajpa.entity.cafe;

import io.beaniejoy.springdatajpa.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cafe_menu")
public class CafeMenu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @Builder.Default
    @OneToMany(mappedBy = "cafeMenu", fetch = FetchType.LAZY)
    private List<MenuOption> menuOptions = new ArrayList<>();

    public void updateCafe(Cafe cafe) {
        if (this.cafe != null) {
            this.cafe.getCafeMenus().remove(this);
        }

        this.cafe = cafe;
        cafe.getCafeMenus().add(this);
    }
}
