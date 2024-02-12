package io.beaniejoy.batch.domain.repository

import io.beaniejoy.batch.domain.entity.Cafe
import org.springframework.data.jpa.repository.JpaRepository

interface CafeRepository : JpaRepository<Cafe, Long>, CafeJdbcRepository {
}