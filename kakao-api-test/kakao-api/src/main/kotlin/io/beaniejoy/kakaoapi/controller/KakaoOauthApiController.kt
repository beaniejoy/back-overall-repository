package io.beaniejoy.kakaoapi.controller

import io.beaniejoy.kakaoapi.model.request.KakaoOauthTokenRequest
import io.beaniejoy.kakaoapi.model.response.KakaoOauthTokenResponse
import io.beaniejoy.kakaoapi.service.KakaoOauthApiService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/kakao/oauth")
class KakaoOauthApiController(
    private val kakaoOauthApiService: KakaoOauthApiService
) {

    @PostMapping("/token")
    fun requestKakaoAccessToken(@RequestBody request: KakaoOauthTokenRequest): KakaoOauthTokenResponse {
        return kakaoOauthApiService.requestAccessToken(request)
    }
}