package chap3

/*
* java 에서는 ExtendsStringKt.java class의 static final method로 생성
* 범용성으로 생각하고 확장메소드를 사용해야 한다. (Util 성으로 범용적으로 사용되는 케이스에 대해서 선언할 것)
*/
fun String.lastChar(): Char = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        setCharAt(length - 1, value)
    }

fun main() {
    val sb = StringBuilder("Kotlin")
    sb.lastChar = '@'
    println(sb)
}