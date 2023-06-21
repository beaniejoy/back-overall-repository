package io.beaniejoy.springdatajpa.repository;

import io.beaniejoy.springdatajpa.entity.cafe.CafeMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeMenuRepository extends JpaRepository<CafeMenu, Long> {
}
