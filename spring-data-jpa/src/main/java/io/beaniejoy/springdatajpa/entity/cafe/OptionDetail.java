package io.beaniejoy.springdatajpa.entity.cafe;

import io.beaniejoy.springdatajpa.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "option_detail")
public class OptionDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal extra;

    @ManyToOne
    @JoinColumn(name = "menu_option_id")
    private MenuOption menuOption;
}