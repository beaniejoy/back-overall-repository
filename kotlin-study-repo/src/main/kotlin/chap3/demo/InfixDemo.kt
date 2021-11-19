package chap3.demo

import chap3.appendAndPrint

fun main() {
    // 일반적인 호출 방식
    println("hello".appendAndPrint(" appended"))
    // 중위 호출 방식(infix call)
    println("hello" appendAndPrint " appended with space")
}