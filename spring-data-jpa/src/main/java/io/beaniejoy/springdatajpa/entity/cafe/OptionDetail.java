package io.beaniejoy.springdatajpa.entity.cafe;

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
@Table(name = "option_detail")
public class OptionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_option_id")
    private MenuOption menuOption;
}