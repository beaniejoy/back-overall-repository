package io.beaniejoy.springdocexample.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.GroupedOpenApi
import org.springdoc.core.customizers.OpenApiCustomiser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class SwaggerConfig {

    @Bean
    fun cafeApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("cafe")
            .packagesToScan("io.beaniejoy.springdocexample.domain.cafe")
            .addOpenApiCustomiser(sortSchemas())
            .build()
    }

    @Bean
    fun memberApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("member")
            .packagesToScan("io.beaniejoy.springdocexample.domain.member")
            .addOpenApiCustomiser(sortSchemas())
            .build()
    }

    @Bean
    fun apiInfo(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("SpringDoc OpenAPI Example")
                    .description("springdoc 예제")
            )
            .addServersItem(Server().url("/"))
    }

    private fun sortSchemas(): OpenApiCustomiser {
        return OpenApiCustomiser { openApi ->
            openApi.components.schemas = TreeMap(openApi.components.schemas)
        }
    }
}