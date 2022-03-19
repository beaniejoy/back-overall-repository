package chap2.part3

import chap2.part3.Color.*

/*
* kotlin enum class 표현
*/
enum class Color(
    val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238); // last 세미콜론 필수

    fun rgb() = (r * 256 + g) * 256 + b
}

fun main(args: Array<String>) {
    println(VIOLET.rgb())
}

/*
* when 도 식이다.
*/
fun getWarmth(color: Color) = when (color) {
    RED, ORANGE, YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE, INDIGO, VIOLET -> "cold"
}

/*
* setOf 로 when 인자에 적용할 수 있음
* setOf 의 순서는 상관 없음 (그리고 동등성 비교를 한다.)
*/
fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty color")
    }
