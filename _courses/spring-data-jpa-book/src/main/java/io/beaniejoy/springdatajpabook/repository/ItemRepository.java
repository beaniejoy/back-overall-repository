package io.beaniejoy.springdatajpabook.repository;

import io.beaniejoy.springdatajpabook.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    // bulk update 수행
    @Modifying
    @Query("update Item i set i.price = i.price * 1.1 where i.stockQuantity < :stockQuantity")
    int bulkPriceUp(@Param("stockQuantity") String stockQuantity);
}
