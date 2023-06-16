package io.beaniejoy.springdatajpa.repository;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface CafeRepository
        extends JpaRepository<Cafe, Long>, JpaSpecificationExecutor<Cafe>, QuerydslPredicateExecutor<Cafe> {

    @Query("select c from Cafe c where c.id = :id")
    Cafe getCafeById(@Param("id") Long id);
}
