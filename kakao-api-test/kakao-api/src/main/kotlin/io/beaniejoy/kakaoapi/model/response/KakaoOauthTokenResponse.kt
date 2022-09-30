package io.beaniejoy.kakaoapi.model.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

// kakao api snake case 고려
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KakaoOauthTokenResponse(
    val tokenType: String = "",
    val accessToken: String = ""
)