package chap7.collection

import chap7.operator.Point

operator fun Point.get(index: Int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException("Invalid Index $index")
    }
}

fun main() {
    val p = Point(10, 20)

    println(p[1])

//    println(p[0, 1])
}