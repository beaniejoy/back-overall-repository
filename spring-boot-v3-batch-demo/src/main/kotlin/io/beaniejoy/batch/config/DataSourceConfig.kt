package io.beaniejoy.batch.config

import com.zaxxer.hikari.HikariDataSource
import io.beaniejoy.batch.common.constant.DataSourceConstants.BATCH_DATASOURCE
import io.beaniejoy.batch.common.constant.DataSourceConstants.SERVICE_DATASOURCE
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration(proxyBeanMethods = false)
class DataSourceConfig {
    @Primary
    @Bean(BATCH_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.batch")
    fun batchDataSource(): DataSource {
        return DataSourceBuilder
            .create()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean(SERVICE_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.service")
    fun serviceDataSource(): DataSource {
        return DataSourceBuilder
            .create()
            .type(HikariDataSource::class.java)
            .build()
    }
}