package io.beaniejoy.batch.job

import io.beaniejoy.batch.common.constant.DataSourceConstants.BATCH_TRANSACTION_MANAGER
import io.beaniejoy.batch.common.constant.DataSourceConstants.SERVICE_TRANSACTION_MANAGER
import io.beaniejoy.batch.domain.entity.Cafe
import io.beaniejoy.batch.domain.repository.CafeRepository
import jakarta.persistence.EntityManagerFactory
import mu.KLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class TransactionTestJobConfig(
    private val jobRepository: JobRepository,
    private val cafeRepository: CafeRepository,
    private val entityManagerFactory: EntityManagerFactory
) {
    companion object : KLogging()

    @Bean
    fun transactionTestJob(
        saveRandomCafesStep: Step
    ): Job {
        return JobBuilder("transactionTestJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(saveRandomCafesStep)
            .build()
    }

    @Bean
    fun saveRandomCafesStep(
        transactionManager: PlatformTransactionManager
    ): Step {
        return StepBuilder("saveRandomCafesStep", jobRepository)
            .chunk<Cafe, String>(10, transactionManager)
            .reader(itemReader())
            .processor { cafe -> "writer: Cafe[${cafe.id}]" }
            .writer { items ->
                logger.info { "item size: ${items.size()}" }
                items.forEach { logger.info { it } }
            }
            .build()
    }   

    private fun itemReader(): ItemReader<Cafe> {
        return JpaPagingItemReaderBuilder<Cafe>()
            .name("itemReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("SELECT c FROM Cafe c")
            .pageSize(100)
            .build()
            .also {
                it.afterPropertiesSet()
            }
    }
}