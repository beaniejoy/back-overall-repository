package chap2

fun testIsPercentage() {
    val number = 101
    val percentage =
        if (number in 0..100)
            number
        else
    //        throw IllegalArgumentException(
    //            "A percentage value must be 0 ~ 100: $number"
    //        )
            return

    println(percentage)
}

fun main() {
    testIsPercentage()
}