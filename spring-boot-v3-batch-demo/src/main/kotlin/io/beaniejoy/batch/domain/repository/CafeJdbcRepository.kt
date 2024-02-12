package io.beaniejoy.batch.domain.repository

import io.beaniejoy.batch.domain.entity.Cafe

interface CafeJdbcRepository {
    fun bulkInsert(cafes: List<Cafe>)
}