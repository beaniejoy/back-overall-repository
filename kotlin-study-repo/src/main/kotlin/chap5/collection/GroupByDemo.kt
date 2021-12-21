package chap5.collection

fun main() {
    val list = listOf("a", "ab", "b")
    println(list.groupBy(String::first))
}