package io.beaniejoy.springdatajpa.data;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class CafeSpecification {

    public static Specification<Cafe> nameEqual(String name) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(name)) return null;

            return cb.equal(root.get("name"), name);
        };
    }

    public static Specification<Cafe> addressLike(String address) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(address)) return null;

            return cb.like(root.get("name"), "%" + address + "%");
        };
    }
}
