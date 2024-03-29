package io.beaniejoy.springdatajpa.config;

import io.beaniejoy.springdatajpa.data.specification.CafeSearch;
import io.beaniejoy.springdatajpa.data.specification.CafeSpecificationWithPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableJpaAuditing
@Configuration
public class AppConfig {

    @Bean
    public CafeSearch cafeSearch() {
//        return new CafeSpecification();
        return new CafeSpecificationWithPredicate();
    }
}
