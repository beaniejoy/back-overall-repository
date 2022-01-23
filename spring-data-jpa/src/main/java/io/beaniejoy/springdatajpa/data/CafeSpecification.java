package io.beaniejoy.springdatajpa.data;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import static org.springframework.data.jpa.domain.Specification.where;

public class CafeSpecification implements CafeSearch{
    private static final String NAME_COL = "name";
    private static final String ADDRESS_COL = "address";

    public Specification<Cafe> nameEqual(String name) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(name)) return null;

            return cb.equal(root.get(NAME_COL), name);
        };
    }

    public Specification<Cafe> addressLike(String address) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(address)) return null;

            return cb.like(root.get(ADDRESS_COL), "%" + address + "%");
        };
    }

    @Override
    public Specification<Cafe> toSpecification(CafeParam param) {
        return where(nameEqual(param.getName()))
                .and(addressLike(param.getAddress()));
    }
}
