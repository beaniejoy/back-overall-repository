package io.beaniejoy.springdatajpabook.repository;

import io.beaniejoy.springdatajpabook.domain.ItemSearch;
import io.beaniejoy.springdatajpabook.entity.Item;

import java.util.List;

public interface CustomItemRepository {
    List<Item> search(ItemSearch itemSearch);
}
