package io.beaniejoy.springdatajpabook.domain;

import io.beaniejoy.springdatajpabook.entity.Item;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ItemSpec {

    public static Specification<Item> itemNameLike(final String itemName) {
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                if (!StringUtils.hasText(itemName)) return null;
                return builder.like(root.<String>get("name"), "%" + itemName + "%");
            }
        };
    }

    public static Specification<Item> priceGreaterThan(final int price) {
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.gt(root.get("price"), price);
            }
        };
    }
}
