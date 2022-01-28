package io.beaniejoy.springdatajpa.data;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import static org.springframework.data.jpa.domain.Specification.where;

public class CafeSpecification implements CafeSearch{
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String ADDRESS_COL = "address";
    private static final String PHONE_NUM_COL = "phoneNumber";

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

    public Specification<Cafe> phoneNumberEqual(String phoneNumber) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(phoneNumber)) return null;

            return cb.equal(root.get(PHONE_NUM_COL), phoneNumber);
        };
    }

    @Override
    public Specification<Cafe> toSpecification(CafeParam param) {
        Specification<Cafe> orderByIdDesc = (root, query, cb) -> {
            query.orderBy(cb.desc(root.get(ID_COL)));
            return cb.and();
        };

        return where(nameEqual(param.getName()))
                .and(addressLike(param.getAddress()))
                .and(phoneNumberEqual(param.getPhoneNumber()))
                .and(orderByIdDesc);
    }
}
