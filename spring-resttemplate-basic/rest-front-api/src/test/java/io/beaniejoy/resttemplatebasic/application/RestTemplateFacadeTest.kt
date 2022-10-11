package io.beaniejoy.resttemplatebasic.application

import io.beaniejoy.resttemplatebasic.entity.Member
import mu.KLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@SpringBootTest
internal class RestTemplateFacadeTest {
    @Value("\${back.uri}")
    private lateinit var backUri: String

    companion object: KLogging()

    @Test
    fun emptyListTest() {
        val result = RestTemplate().getForObject<List<Member>>("${backUri}/api/back/members/empty")

        logger.info { "result = ${result}" }
    }
}