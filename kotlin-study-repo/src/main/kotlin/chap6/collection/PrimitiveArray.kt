package chap6.collection

/*
* Array<Int> 같은 경우 타입인자가 객체 타입으로 지정된다.
* (java -> Integer[])
*
* Primitive type 배열로 선언하고 싶을 때 코틀린에서 제공하는 별도 클래스 사용하면 된다.
* IntArray, ByteArray, CharArray, BooleanArray 등...
*/

fun main() {
    // 둘다 같은 표현
    val fiveZeros = IntArray(5)
    val fiveZerosToo = intArrayOf(0, 0, 0, 0, 0)

    val squares = IntArray(5) { i -> (i + 1) * (i + 1) }
    println(squares.joinToString()) // 1, 4, 9, 16, 25

    val intArr = Array<Int>(10) { i -> i }
    intArr.toIntArray() // Primitive type 배열로 전환
}