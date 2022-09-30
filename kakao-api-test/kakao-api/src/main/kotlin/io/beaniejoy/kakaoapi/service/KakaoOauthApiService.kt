package io.beaniejoy.kakaoapi.service

import io.beaniejoy.kakaoapi.model.request.KakaoOauthTokenRequest
import io.beaniejoy.kakaoapi.model.response.KakaoOauthTokenResponse
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate

@Service
class KakaoOauthApiService(
    @Value("\${kakao.kauth}")
    private val kakaoKauthUrl: String,
    private val restTemplate: RestTemplate
) {
    companion object: KLogging()

    fun requestAccessToken(request: KakaoOauthTokenRequest): KakaoOauthTokenResponse {
        return try {
            restTemplate.postForObject(
                "${kakaoKauthUrl}/oauth/token",
                request.toParameterMap(),
                KakaoOauthTokenResponse::class.java
            )!!
        } catch (e: HttpStatusCodeException) {
            val errorMessage = e.responseBodyAsString
            logger.error { errorMessage }
            throw RuntimeException(errorMessage)
        }
    }
}