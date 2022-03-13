package fastcampus.spring.batch.part3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class ChunkProcessingConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ChunkProcessingConfiguration(JobBuilderFactory jobBuilderFactory,
                                        StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job chunkProcessingJob() {
        return jobBuilderFactory.get("chunkProcessingJob")
                .incrementer(new RunIdIncrementer())
                .start(taskBaseStep())
                .next(chunkBaseStep(null))
                .build();
    }

    // #2 JobParameters 가져오기 - SpEL 사용하여 가져오기 (@Value)
    @Bean
    @JobScope
    public Step chunkBaseStep(@Value("#{jobParameters[chunkSize]}") String chunkSize) {
        // 100개 데이터를 10개씩 chunk로 나눠서 실행
        return stepBuilderFactory.get("chunkBaseStep")
                // <Input, Output>
                .<String, String>chunk(StringUtils.isNotEmpty(chunkSize) ? Integer.parseInt(chunkSize) : 10)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    // 생성한 100개의 데이터를 List 형식으로 Reader 에 넣어줌
    private ItemReader<String> itemReader() {
        return new ListItemReader<>(getItems());
    }

    // Reader -> Writer 로 넘어가기전에 중간 작업이 필요한 경우 Processor 를 통해 작업
    private ItemProcessor<String, String> itemProcessor() {
        return item -> item + ", Spring Batch";
    }

    //
    private ItemWriter<String> itemWriter() {
        return items -> log.info("chunk item size : {}", items.size());
//        return items -> items.forEach(log::info);
    }

    @Bean
    public Step taskBaseStep() {
        return stepBuilderFactory.get("taskBaseStep")
                .tasklet(tasklet(null))
                .build();
    }

    // task 방식으로 chunk 처럼 배치 실행하기
    @Bean
    @StepScope
    public Tasklet tasklet(@Value("#{jobParameters[chunkSize]}") String value) {
        List<String> items = getItems();
        return (contribution, chunkContext) -> {
            StepExecution stepExecution = contribution.getStepExecution();
//            JobParameters jobParameters = stepExecution.getJobParameters();

            // #1 JobParameter 를 사용해서 동적으로 chunkSize 설정하기
            // 직접 JobParameters 객체를 가져와서 chunkSize 값을 가져온다.
//            String value = jobParameters.getString("chunkSize", "10");
            int chunkSize = StringUtils.isNotEmpty(value) ? Integer.parseInt(value) : 10;
            int fromIndex = stepExecution.getReadCount();   // volatile int readCount = 0; > 0에서 시작
            int toIndex = fromIndex + chunkSize;

            if (fromIndex >= items.size()) {
                return RepeatStatus.FINISHED;
            }

            List<String> subList = items.subList(fromIndex, toIndex);

            log.info("task item size : {}", subList.size());

            stepExecution.setReadCount(toIndex);

            return RepeatStatus.CONTINUABLE;
        };
    }

    private List<String> getItems() {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add(i + " Hello");
        }

        return items;
    }
}
