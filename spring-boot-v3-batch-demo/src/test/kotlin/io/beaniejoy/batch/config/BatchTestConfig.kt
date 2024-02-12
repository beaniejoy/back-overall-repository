package io.beaniejoy.batch.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@EnableAutoConfiguration
@Import(BatchConfig::class, DataSourceConfig::class, JpaConfig::class)
class BatchTestConfig {
}