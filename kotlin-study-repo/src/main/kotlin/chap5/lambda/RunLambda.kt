package chap5.lambda

fun salute() = println("Salute!")

fun main() {
    run { println(50) }

    run(::salute)
}