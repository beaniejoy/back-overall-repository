package chap3.demo

import chap3.printVararg

fun main() {
    val args = arrayOf("hello", "world", "vararg")
    // 배열을 명시적으로 풀어서 배열의 각 원소가 인자로 전달되게 해야함
    printVararg(*args, "plus")  // 인자를 추가할 수 있다.

    // hashMapOf(vararg pairs: Pair<K, V>
    val arrArgs = arrayOf("one" to "value1", "two" to "value2", "three" to "value3")
    val mapArgs = hashMapOf(*arrArgs)
}