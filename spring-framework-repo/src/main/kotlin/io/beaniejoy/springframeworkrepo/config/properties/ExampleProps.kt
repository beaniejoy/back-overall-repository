package io.beaniejoy.springframeworkrepo.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component

/**
 * setter를 통한 설정값 주입
 * no constructor 필수, getter만으로는 주입 실패
 * 이 때는 @Component, @Bean을 통한 빈으로 생성 가능
 */
@Component
@ConfigurationProperties(prefix = "example.one")
data class ExampleProps(
    var one: Int = 0,
    var two: Int = 0,
    var text: String = "",
    var hello: String = ""
)

/**
 * constructor 통한 설정값 주입
 * 이 때는 @Component, @Bean을 통한 빈으로 생성 불가능
 * (필히 @ConfigurationPropertiesScan, @EnableConfigurationProperties 설정)
 */
@ConfigurationProperties(prefix = "example.another")
@ConstructorBinding
data class ExampleProps2(
    val one: Int,
    val two: Int,
    val text: String,
    val hello: String
)