package io.beaniejoy.entityexample.entity.cafe;

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
@Table(name = "menu_option")
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "cafe_menu_id")
    private CafeMenu cafeMenu;

    @OneToMany(mappedBy = "menuOption", fetch = FetchType.LAZY)
    private List<OptionDetail> optionDetails;
}
