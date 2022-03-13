package fastcampus.spring.batch.part3;

import fastcampus.spring.batch.source.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class JdbcItemReaderConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    public JdbcItemReaderConfiguration(JobBuilderFactory jobBuilderFactory,
                                       StepBuilderFactory stepBuilderFactory,
                                       DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public Job jdbcItemReaderJob() throws Exception {
        return jobBuilderFactory.get("jdbcItemReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(jdbcCursorStep())
                .next(jdbcPagingStep())
                .build();
    }

    @Bean
    public Step jdbcCursorStep() throws Exception {
        return stepBuilderFactory.get("jdbcCursorStep")
                .<Person, Person>chunk(10)
                .reader(jdbcCursorItemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Step jdbcPagingStep() throws Exception {
        return stepBuilderFactory.get("jdbcPagingStep")
                .<Person, Person>chunk(10)
                .reader(jdbcPagingItemReader())
                .writer(itemWriter())
                .build();
    }

    // JDBC Cursor 기반 ItemReader
    private JdbcCursorItemReader<Person> jdbcCursorItemReader() throws Exception {
        JdbcCursorItemReader<Person> itemReader = new JdbcCursorItemReaderBuilder<Person>()
                .name("jdbcCursorItemReader")
                .dataSource(dataSource) // datasource 설정
                .sql("select id, name, age, address from person")   // sql 직접 설정
                .rowMapper((rs, rowNum) -> new Person(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4))
                )
                .build();
        itemReader.afterPropertiesSet();

        return itemReader;
    }

    // JDBC Paging 기반 ItemReader
    // queryProvider 를 이용한 방법도 존재
    private JdbcPagingItemReader<Person> jdbcPagingItemReader() throws Exception {
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.DESCENDING);

        JdbcPagingItemReader<Person> itemReader = new JdbcPagingItemReaderBuilder<Person>()
                .name("jdbcPagingItemReader")
                .dataSource(dataSource)
                .selectClause("id, name, age, address") // select projection 설정
                .fromClause("from person")  // from 절 설정
                .rowMapper((rs, rowNum) -> new Person(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4))
                )
                .pageSize(10)   // page size 설정 (필수 설정값)
                .sortKeys(sortKeys) // 필수 설정값
                .build();
        itemReader.afterPropertiesSet();

        return itemReader;
    }

    private ItemWriter<Person> itemWriter() {
        return items -> log.info(items.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ")));
    }
}
