package io.beaniejoy.kakaoapi.config

import org.apache.http.client.HttpClient
import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.nio.charset.StandardCharsets

@Configuration
class RestTemplateConfig {
    @Bean
    fun poolingHttpClientConnectionManager(): PoolingHttpClientConnectionManager {
        return PoolingHttpClientConnectionManager().apply {
            this.maxTotal = 100
            this.defaultMaxPerRoute = 10
        }
    }

    @Bean
    fun requestConfig(): RequestConfig {
        return RequestConfig.custom()
            .setConnectionRequestTimeout(3000)
            .setConnectTimeout(3000)
            .setSocketTimeout(3000)
            .build()
    }

    @Bean
    fun httpClient(
        poolingHttpClientConnectionManager: PoolingHttpClientConnectionManager,
        requestConfig: RequestConfig,
    ): CloseableHttpClient {
        return HttpClientBuilder.create()
            .setConnectionManager(poolingHttpClientConnectionManager)
            .setDefaultRequestConfig(requestConfig)
            .build()
    }

    /**
     * 로깅 인터셉터를 이용한 RestTemplate 로깅 설정하기
     */
    @Bean
    fun restTemplate(
        restTemplateBuilder: RestTemplateBuilder,
        httpClient: HttpClient,
    ): RestTemplate {
        val factory = HttpComponentsClientHttpRequestFactory(httpClient)

        return restTemplateBuilder
            //로깅 인터셉터에서 Stream 일회성 소비하는 것을 방지
            .requestFactory { BufferingClientHttpRequestFactory(factory) }
            .additionalMessageConverters(StringHttpMessageConverter(StandardCharsets.UTF_8))
            .additionalInterceptors(RestTemplateLoggingInterceptor())
            .build()
    }
}