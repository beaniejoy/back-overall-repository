package io.beaniejoy.springdocexample.domain.cafe.repository

import io.beaniejoy.springdocexample.domain.cafe.entity.Cafe
import org.springframework.data.jpa.repository.JpaRepository

interface CafeRepository: JpaRepository<Cafe, Long> {
}