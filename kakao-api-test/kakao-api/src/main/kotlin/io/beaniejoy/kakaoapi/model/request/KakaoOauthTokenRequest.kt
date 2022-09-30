package io.beaniejoy.kakaoapi.model.request

import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

data class KakaoOauthTokenRequest(
    val grantType: String,
    val clientId: String,
    val redirectUri: String,
    val code: String
) {
    fun toParameterMap(): MultiValueMap<String, String> {
        return LinkedMultiValueMap<String, String>().apply {
            this["grant_type"] = grantType
            this["client_id"] = clientId
            this["redirect_uri"] = redirectUri
            this["code"] = code
        }
    }
}