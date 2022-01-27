package testpack

fun main() {
    val strArr = arrayOf("hello", "good")
    ArrFunc.testArr(*strArr)
    println(mapOf(1 to 3, 2 to 3, 3 to 3).javaClass)
}