package io.beaniejoy.springdatajpabook.entity;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;        //이름
    private int price;          //가격
    private int stockQuantity;  //재고수량

}
