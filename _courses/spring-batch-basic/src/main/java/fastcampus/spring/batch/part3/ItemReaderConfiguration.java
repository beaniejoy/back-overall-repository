package fastcampus.spring.batch.part3;

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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class ItemReaderConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    public ItemReaderConfiguration(JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilderFactory,
                                   DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public Job itemReaderJob() throws Exception {
        return jobBuilderFactory.get("itemReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(customItemReaderStep())
                .next(csvFileStep())
                .next(jdbcCursorStep())
                .next(jdbcPagingStep())
                .build();
    }

    @Bean
    public Step customItemReaderStep() {
        return stepBuilderFactory.get("customItemReaderStep")
                .<Person, Person>chunk(10)
                .reader(new CustomItemReader<>(getItems()))
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Step csvFileStep() throws Exception {
        return stepBuilderFactory.get("csvFileStep")
                .<Person, Person>chunk(10)
                .reader(csvFileItemReader())
                .writer(itemWriter())
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

    // customItemReaderStep 을 위한 데이터 구성
    private List<Person> getItems() {
        List<Person> items = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            items.add(new Person(i + 1, "test name " + i, 20 + i, "test address"));
        }

        return items;
    }

    // csv file reader
    private FlatFileItemReader<Person> csvFileItemReader() throws Exception {
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "name", "age", "address");
        lineMapper.setLineTokenizer(tokenizer);

        // csv value 내용 추출 > jdbc resultSet 에서 뽑는것과 비슷
        lineMapper.setFieldSetMapper(fieldSet -> {
            int id = fieldSet.readInt("id");
            String name = fieldSet.readString("name");
            int age = fieldSet.readInt("age");
            String address = fieldSet.readString("address");

            return new Person(id, name, age, address);
        });
        lineMapper.afterPropertiesSet();

        FlatFileItemReader<Person> itemReader = new FlatFileItemReaderBuilder<Person>()
                .name("csvFileItemReader")
                .encoding("UTF-8")
                .resource(new ClassPathResource("test.csv")) // ClassPathResource: Spring에서 제공하는 resources 파일 앍울 수 있음
                .linesToSkip(1) // 첫번째 row (title)는 skip
                .lineMapper(lineMapper) // 한 줄씩 가면서 mapping 하는 클래스 설정
                .build();
        itemReader.afterPropertiesSet(); // itemReader 의 필수 설정값이 제대로 되어있는지 check

        return itemReader;
    }

    // JDBC Cursor 기반 ItemReader
    private JdbcCursorItemReader<Person> jdbcCursorItemReader() throws Exception {
        JdbcCursorItemReader<Person> itemReader = new JdbcCursorItemReaderBuilder<Person>()
                .name("jdbcCursorItemReader")
                .dataSource(dataSource)
                .sql("select id, name, age, address from person")
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
                .selectClause("id, name, age, address")
                .fromClause("from person")
                .rowMapper((rs, rowNum) -> new Person(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4))
                )
                .pageSize(10)
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
