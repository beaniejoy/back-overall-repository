package io.beaniejoy.batch.domain.repository

import io.beaniejoy.batch.common.constant.DataSourceConstants.SERVICE_TRANSACTION_MANAGER
import io.beaniejoy.batch.domain.entity.Cafe
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(SERVICE_TRANSACTION_MANAGER)
class CafeJdbcRepositoryImpl(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) : CafeJdbcRepository {
    companion object {
        const val TABLE_NAME = "cafes"
    }

    override fun bulkInsert(cafes: List<Cafe>) {
        val sql = String.format(
            """
            |INSERT INTO `%s` (name, address, phone_number, description)
            |VALUES (:name, :address, :phoneNumber, :description)
            """.trimMargin(),
            TABLE_NAME
        )

        val params = cafes
            .map { BeanPropertySqlParameterSource(it) }
            .toTypedArray()

        namedParameterJdbcTemplate.batchUpdate(sql, params)
    }
}