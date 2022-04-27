package io.beaniejoy.springdocexample.common

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema


@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Parameters(
    Parameter(
        name = "page",
        description = "페이지 번호",
        `in` = ParameterIn.QUERY,
        schema = Schema(type = "integer", defaultValue = "0")
    ),
    Parameter(
        name = "size",
        description = "한 페이지 당 데이터 개수",
        `in` = ParameterIn.QUERY,
        schema = Schema(type = "integer", defaultValue = "20")
    )
)
annotation class ApiPageable {
}