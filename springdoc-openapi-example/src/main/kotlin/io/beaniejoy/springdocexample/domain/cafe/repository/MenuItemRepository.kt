package io.beaniejoy.springdocexample.domain.cafe.repository

import io.beaniejoy.springdocexample.domain.cafe.entity.MenuItem
import org.springframework.data.jpa.repository.JpaRepository

interface MenuItemRepository: JpaRepository<MenuItem, Long> {
}