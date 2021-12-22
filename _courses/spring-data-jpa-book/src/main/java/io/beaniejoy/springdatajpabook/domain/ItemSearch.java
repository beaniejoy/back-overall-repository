package io.beaniejoy.springdatajpabook.domain;

import io.beaniejoy.springdatajpabook.entity.Item;
import org.springframework.data.jpa.domain.Specification;

import static io.beaniejoy.springdatajpabook.domain.ItemSpec.*;
import static org.springframework.data.jpa.domain.Specification.where;

public class ItemSearch {

    private String itemName;
    private int price;

    public String getItemName() {
        return itemName;
    }

    public ItemSearch(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Specification<Item> toSpecification() {
        return where(itemNameLike(itemName).and(priceGreaterThan(price)));
    }
}
