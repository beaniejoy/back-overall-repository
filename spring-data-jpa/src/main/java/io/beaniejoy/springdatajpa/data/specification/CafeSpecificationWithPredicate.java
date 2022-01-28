package io.beaniejoy.springdatajpa.data.specification;

import io.beaniejoy.springdatajpa.data.CafeColumn;
import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CafeSpecificationWithPredicate implements CafeSearch {

    @Override
    public Specification<Cafe> toSpecification(CafeParam param) {
        return searchWith(
                param.getName(),
                param.getAddress(),
                param.getPhoneNumber());
    }

    private Specification<Cafe> searchWith(String name, String address, String phoneNumber) {
        return (root, query, cb) -> {
            List<Predicate> predicates = createPredicateListWithParams(name, address, phoneNumber, root, cb);
            query.orderBy(cb.desc(root.get(CafeColumn.ID_COL)));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private List<Predicate> createPredicateListWithParams(String name,
                                                          String address,
                                                          String phoneNumber,
                                                          Root<Cafe> root,
                                                          CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(name))
            predicates.add(cb.equal(root.get(CafeColumn.NAME_COL), name));

        if (StringUtils.hasText(address))
            predicates.add(cb.like(root.get(CafeColumn.ADDRESS_COL), "%" + address + "%"));

        if (StringUtils.hasText(phoneNumber))
            predicates.add(cb.equal(root.get(CafeColumn.PHONE_NUM_COL), phoneNumber));

        return predicates;
    }
}
