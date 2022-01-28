package io.beaniejoy.springdatajpa.data.specification;

import io.beaniejoy.springdatajpa.data.CafeColumn;
import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import static org.springframework.data.jpa.domain.Specification.where;

public class CafeSpecification implements CafeSearch{
    public Specification<Cafe> nameEqual(String name) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(name)) return null;

            return cb.equal(root.get(CafeColumn.NAME_COL), name);
        };
    }

    public Specification<Cafe> addressLike(String address) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(address)) return null;

            return cb.like(root.get(CafeColumn.ADDRESS_COL), "%" + address + "%");
        };
    }

    public Specification<Cafe> phoneNumberEqual(String phoneNumber) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(phoneNumber)) return null;

            return cb.equal(root.get(CafeColumn.PHONE_NUM_COL), phoneNumber);
        };
    }

    @Override
    public Specification<Cafe> toSpecification(CafeParam param) {
        Specification<Cafe> orderByIdDesc = (root, query, cb) -> {
            query.orderBy(cb.desc(root.get(CafeColumn.ID_COL)));
            return cb.and();
        };

        return where(nameEqual(param.getName()))
                .and(addressLike(param.getAddress()))
                .and(phoneNumberEqual(param.getPhoneNumber()))
                .and(orderByIdDesc);
    }
}
