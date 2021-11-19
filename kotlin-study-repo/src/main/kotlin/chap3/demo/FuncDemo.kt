package chap3.demo

// 다른패키지에 있으면 역시 import를 해야 한다.
import chap3.lastChar

fun main(args: Array<String>) {
    println("hello world!".lastChar())
}