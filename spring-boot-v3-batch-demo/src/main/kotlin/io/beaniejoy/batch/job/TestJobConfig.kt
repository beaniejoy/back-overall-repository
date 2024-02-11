package io.beaniejoy.batch.job

import io.beaniejoy.batch.common.constant.DataSourceConstants.BATCH_TRANSACTION_MANAGER
import mu.KLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class TestJobConfig(
    private val jobRepository: JobRepository
) {
    companion object: KLogging()

    @Bean
    fun testJob(
        simpleStep1: Step,
        simpleStep2: Step
    ): Job {
        return JobBuilder("testJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(simpleStep1)
            .next(simpleStep2)
            .build()
    }

    @Bean
    fun simpleStep1(
        @Qualifier(BATCH_TRANSACTION_MANAGER) transactionManager: PlatformTransactionManager
    ): Step {
        val testTasklet = Tasklet { _, _ ->
            logger.info { ">>>>>> this is step1" }
            RepeatStatus.FINISHED
        }

        return StepBuilder("simpleStep1", jobRepository)
            .tasklet(testTasklet, transactionManager)
            .build()
    }

    @Bean
    fun simpleStep2(
        @Qualifier(BATCH_TRANSACTION_MANAGER) transactionManager: PlatformTransactionManager
    ): Step {
        val testTasklet = Tasklet { _, _ ->
            logger.info { ">>>>>> this is step2" }
            RepeatStatus.FINISHED
        }

        return StepBuilder("simpleStep2", jobRepository)
            .tasklet(testTasklet, transactionManager)
            .build()
    }
}