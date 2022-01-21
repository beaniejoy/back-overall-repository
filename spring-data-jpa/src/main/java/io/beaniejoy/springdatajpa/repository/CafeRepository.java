package io.beaniejoy.springdatajpa.repository;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CafeRepository extends JpaRepository<Cafe, Long>, JpaSpecificationExecutor<Cafe> {
}
