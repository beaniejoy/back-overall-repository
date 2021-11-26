package chap6.primitive

import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    // 초기화 식에서 setScale을 설정해줘야 한다.
    val big1 = BigDecimal("320.1234").setScale(0, RoundingMode.CEILING)
    val big2 = BigDecimal("320.1234")
    // 이렇게 하면 에러 발생 (ArithmeticException: Rounding necessary)
    // one.setScale(0, RoundingMode.CEILING)
    println("${big2}")
    println("toLong(): ${big2.toLong()}")
    println("longValueExact(): ${big1.longValueExact()}")
}