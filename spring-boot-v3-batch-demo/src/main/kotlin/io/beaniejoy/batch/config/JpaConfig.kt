package io.beaniejoy.batch.config

import io.beaniejoy.batch.common.constant.DataSourceConstants.SERVICE_DATASOURCE
import io.beaniejoy.batch.common.constant.DataSourceConstants.SERVICE_ENTITY_MANAGER
import io.beaniejoy.batch.common.constant.DataSourceConstants.SERVICE_TRANSACTION_MANAGER
import org.hibernate.cfg.SchemaToolingSettings
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["io.beaniejoy.batch.domain.repository"],
    entityManagerFactoryRef = SERVICE_ENTITY_MANAGER,
    transactionManagerRef = SERVICE_TRANSACTION_MANAGER
)
class JpaConfig(
    private val jpaProperties: JpaProperties,
    private val hibernateProperties: HibernateProperties
) {

    @Primary
    @Bean(SERVICE_ENTITY_MANAGER)
    fun serviceEntityManager(
        @Qualifier(SERVICE_DATASOURCE) serviceDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        val ddlAuto: String? = hibernateProperties.ddlAuto

        val mergedJpaProperties = Properties().apply properties@{
            this.putAll(jpaProperties.properties)
            ddlAuto?.also {
                this[SchemaToolingSettings.HBM2DDL_AUTO] = it
            }
        }

        return LocalContainerEntityManagerFactoryBean().apply {
            this.jpaVendorAdapter = HibernateJpaVendorAdapter()
            this.setJpaProperties(mergedJpaProperties)
            this.dataSource = serviceDataSource
            this.persistenceUnitName = "servicePU"
            this.setPackagesToScan("io.beaniejoy.batch.domain.entity")
        }
    }

    @Bean(SERVICE_TRANSACTION_MANAGER)
    fun serviceTransactionManager(
        @Qualifier(SERVICE_ENTITY_MANAGER) serviceEntityManager: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager().apply {
            this.entityManagerFactory = serviceEntityManager.`object`
        }
    }

    // for jdbc
    @Bean
    fun jdbcTemplate(
        @Qualifier(SERVICE_DATASOURCE) serviceDataSource: DataSource
    ): JdbcTemplate {
        return JdbcTemplate(serviceDataSource)
    }
}