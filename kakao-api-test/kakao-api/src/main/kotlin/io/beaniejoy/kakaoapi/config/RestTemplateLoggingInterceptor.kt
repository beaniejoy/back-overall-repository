package io.beaniejoy.kakaoapi.config

import mu.KLogging
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.util.StreamUtils
import java.net.URI
import java.nio.charset.StandardCharsets

class RestTemplateLoggingInterceptor : ClientHttpRequestInterceptor {
    companion object : KLogging() {
        private val DEFAULT_ENCODING = StandardCharsets.UTF_8
    }

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution,
    ): ClientHttpResponse {
        logRequest(request = request, body = body)

        val response = execution.execute(request, body)

        logResponse(response = response, uri = request.uri)

        return response
    }


    private fun logRequest(request: HttpRequest, body: ByteArray) {
        logger.info {
            """
            |#### [REQUEST] ####
            |#### [URI] ${request.uri}
            |#### [Method] ${request.method}
            |#### Request Body: ${String(body, DEFAULT_ENCODING)}
        """.trimMargin() }
    }

    private fun logResponse(response: ClientHttpResponse, uri: URI) {
        logger.info {
            """
            |#### [RESPONSE] ####
            |#### [URI] ${uri}
            |#### [Status Code] ${response.statusCode}
            |#### Response Body: ${StreamUtils.copyToString(response.body, DEFAULT_ENCODING)}
        """.trimMargin()
        }
    }
}