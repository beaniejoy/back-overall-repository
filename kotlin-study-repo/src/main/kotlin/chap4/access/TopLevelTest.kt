package chap4.access

// 최상위 선언 클래스
private class Test {
    fun hello() {
        println("hello world!")
    }
}

// 최상위 선언 함수
private fun helloFunc() = println("hello world fun!")

// protected 최상위 선언은 불가능
// protected fun protectedHello() = println("hello world protected!")

fun main() {
    Test().hello()
    helloFunc()
}
