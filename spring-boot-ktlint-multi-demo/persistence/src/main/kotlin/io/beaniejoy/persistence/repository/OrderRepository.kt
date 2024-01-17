package io.beaniejoy.persistence.repository

import io.beaniejoy.domain.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long> {
}