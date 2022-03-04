package fastcampus.spring.batch.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ScopeTestBatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ScopeTestJobTasklet testJobTasklet;

    public ScopeTestBatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ScopeTestJobTasklet testJobTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.testJobTasklet = testJobTasklet;
    }

    @Bean
    public Job testJob() {
        return jobBuilderFactory.get("scopeTestBatchJob")
                .incrementer(new RunIdIncrementer())
                .start(testStep())
                .next(testStep())
                .build();
    }

    @Bean
    public Step testStep() {
        return stepBuilderFactory.get("scopeTestBatchStep")
                .tasklet(testJobTasklet)
                .build();
    }
}
