package chap4.delegation

// 상속 받을 때 기본 생성자에서 상속받은 클래스에서는 var, val를 붙이지 않는다.
open class LevelOne(
    val prop: Int,
    val prop2: String
) {
    open fun hello() {
        println("hello Level One")
    }
}

// LevelOne 의 property에 대해서는 val, var를 붙이지 않는다.
class LevelTwo(
    prop: Int,  // val prop: Int (x)
    prop2: String,
    val prop3: Boolean
) : LevelOne(prop, prop2) {
    override fun hello() {
        println("hello Level Two")
    }
}

fun main() {
    val objTwo: LevelOne =
        LevelTwo(1, "hello", true)
    objTwo.hello()
}