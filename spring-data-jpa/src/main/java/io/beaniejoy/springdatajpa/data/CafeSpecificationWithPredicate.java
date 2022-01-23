package io.beaniejoy.springdatajpa.data;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CafeSpecificationWithPredicate implements CafeSearch{

    public Specification<Cafe> searchWith(String name, String address) {
        return (root, query, cb) -> {
            List<Predicate> predicates = constructPredicateListWithParams(name, address, root, cb);
            query.orderBy(cb.desc(root.get("id")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private List<Predicate> constructPredicateListWithParams(String name,
                                                                    String address,
                                                                    Root<Cafe> root,
                                                                    CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (name != null && !name.isEmpty())
            cb.equal(root.get("name"), name);

        if (address != null && !address.isEmpty())
            cb.equal(root.get("address"), address);

        return predicates;
    }

    @Override
    public Specification<Cafe> toSpecification(CafeParam param) {
        return searchWith(
                param.getName(),
                param.getAddress());
    }
}
