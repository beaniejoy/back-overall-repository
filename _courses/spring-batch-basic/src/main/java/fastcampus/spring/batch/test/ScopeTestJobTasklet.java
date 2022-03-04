package fastcampus.spring.batch.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@StepScope
public class ScopeTestJobTasklet implements Tasklet {
    private String name;
    private String value;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info(">>>>>>>>>>>>>>>>>> name: " + name);
        log.info(">>>>>>>>>>>>>>>>>> value: " + value);
        StepExecution stepExecution = contribution.getStepExecution();
        Long id = stepExecution.getId();

        updateInfo(id + " Task", id +" value");

        return RepeatStatus.FINISHED;
    }

    public void updateInfo(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
