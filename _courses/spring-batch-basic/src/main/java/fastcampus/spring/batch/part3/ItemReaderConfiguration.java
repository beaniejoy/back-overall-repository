package fastcampus.spring.batch.part3;

import fastcampus.spring.batch.part3.custom.CustomItemReader;
import fastcampus.spring.batch.source.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class ItemReaderConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ItemReaderConfiguration(JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job itemReaderJob() throws Exception {
        return jobBuilderFactory.get("itemReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(customItemReaderStep())
                .next(csvFileStep())
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

    // 1. customItemReaderStep 을 위한 데이터 구성
    private List<Person> getItems() {
        List<Person> items = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            items.add(new Person(i + 1, "test name " + i, 20 + i, "test address"));
        }

        return items;
    }

    // 2. csv file reader
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

    private ItemWriter<Person> itemWriter() {
        return items -> log.info(items.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ")));
    }
}
