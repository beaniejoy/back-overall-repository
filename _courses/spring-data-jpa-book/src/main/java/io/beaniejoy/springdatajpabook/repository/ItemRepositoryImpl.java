package io.beaniejoy.springdatajpabook.repository;

import io.beaniejoy.springdatajpabook.domain.ItemSearch;
import io.beaniejoy.springdatajpabook.entity.Item;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ItemRepositoryImpl extends QuerydslRepositorySupport implements CustomItemRepository{

    public ItemRepositoryImpl() {
        super(Item.class);
    }

    @Override
    public List<Item> search(ItemSearch itemSearch) {
        return null;
    }
}
