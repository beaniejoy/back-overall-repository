package io.beaniejoy.springdatajpabook.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/*
* @EnableSpringDataWebSupport
* DomainClassConverter, PageableHandlerMethodArgumentResolver, SortHandlerMethodArgumentResolver 제공
* spring-boot-starter-web 모듈을 적용하면 따로 설정안해도된다.
*/
@Configuration
@EnableJpaRepositories(basePackages = "io.beaniejoy.springdatajpabook.repository")
//@EnableSpringDataWebSupport
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:h2:mem:testdb");
        hikariConfig.setUsername("sa");
        hikariConfig.setPassword("");

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("io.beaniejoy.springdatajpabook");
        factoryBean.setDataSource(dataSource());

        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.show_sql", true);
        props.put("hibernate.format_sql", true);
        props.put("hibernate.use_sql_comments", true);
        props.put("hibernate.hbm2ddl.auto", "create");
        factoryBean.setJpaProperties(props);

        return factoryBean;
    }
}
