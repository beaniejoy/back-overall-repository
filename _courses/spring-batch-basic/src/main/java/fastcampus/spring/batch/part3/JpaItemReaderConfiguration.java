package fastcampus.spring.batch.part3;

import fastcampus.spring.batch.part3.custom.CustomJpaQueryProvider;
import fastcampus.spring.batch.source.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.database.orm.JpaQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class JpaItemReaderConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    public JpaItemReaderConfiguration(JobBuilderFactory jobBuilderFactory,
                                      StepBuilderFactory stepBuilderFactory,
                                      EntityManagerFactory entityManagerFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public Job JpaItemReaderJob() throws Exception {
        return jobBuilderFactory.get("jpaItemReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(JpaCursorStep())
                .next(JpaPagingStep())
                .build();
    }

    @Bean
    public Step JpaCursorStep() throws Exception {
        return stepBuilderFactory.get("jpaCursorStep")
                .<Person, Person>chunk(10)
                .reader(jpaCursorItemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Step JpaPagingStep() throws Exception {
        return stepBuilderFactory.get("jpaPagingStep")
                .<Person, Person>chunk(10)
                .reader(jpaPagingItemReader())
                .writer(itemWriter())
                .build();
    }

    private JpaCursorItemReader<Person> jpaCursorItemReader() throws Exception {
        JpaCursorItemReader<Person> itemReader = new JpaCursorItemReaderBuilder<Person>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select p from Person p")  // JPQL 로 작성해야함
                .build();
        itemReader.afterPropertiesSet();

        return itemReader;
    }

    private JpaPagingItemReader<Person> jpaPagingItemReader() throws Exception {
        JpaPagingItemReader<Person> itemReader = new JpaPagingItemReaderBuilder<Person>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryProvider(createQueryProvider())
                .build();
        itemReader.afterPropertiesSet();

        return itemReader;
    }

    private JpaQueryProvider createQueryProvider() {
        return new CustomJpaQueryProvider();
    }

    private ItemWriter<Person> itemWriter() {
        return items -> log.info(items.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ")));
    }
}
