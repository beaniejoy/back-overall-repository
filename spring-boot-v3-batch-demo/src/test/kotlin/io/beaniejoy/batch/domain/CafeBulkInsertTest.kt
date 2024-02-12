package io.beaniejoy.batch.domain

import io.beaniejoy.batch.config.BatchTestConfig
import io.beaniejoy.batch.domain.entity.Cafe
import io.beaniejoy.batch.domain.repository.CafeRepository
import io.beaniejoy.batch.utils.EasyRandomFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.JobRepositoryTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import java.util.stream.IntStream

@SpringBatchTest
@SpringBootTest(
    classes = [CafeBulkInsertTest.CafeBulkInsertTestJobConfig::class, BatchTestConfig::class]
)
class CafeBulkInsertTest(
    @Autowired
    private val jobRepositoryTestUtils: JobRepositoryTestUtils,
    @Autowired
    private val jobLauncherTestUtils: JobLauncherTestUtils
) {
    @AfterEach
    fun tearDown() {
        jobRepositoryTestUtils.removeJobExecutions()
    }

    @Test
    fun executeTest() {
        jobLauncherTestUtils.launchJob()
    }

    @Configuration
    class CafeBulkInsertTestJobConfig(
        private val jobRepository: JobRepository,
        private val cafeRepository: CafeRepository
    ) {
        @Bean
        fun cafeBulkInsertTestJob(cafeBulkInsertTestStep: Step): Job {
            return JobBuilder("cafeBulkInsertTestJob", jobRepository)
                .incrementer(RunIdIncrementer())
                .start(cafeBulkInsertTestStep)
                .build()
        }

        @Bean
        fun cafeBulkInsertTestStep(transactionManager: PlatformTransactionManager): Step {
            val testTasklet = Tasklet { _, _ ->
                val easyRandom = EasyRandomFactory.newCafeInstance()

                val cafes = IntStream.range(0, 10_000)
                    .parallel()
                    .mapToObj { easyRandom.nextObject(Cafe::class.java) }
                    .toList()

                cafeRepository.bulkInsert(cafes)

                RepeatStatus.FINISHED
            }

            return StepBuilder("cafeBulkInsertTestStep", jobRepository)
                .tasklet(testTasklet, transactionManager)
                .build()
        }
    }
}