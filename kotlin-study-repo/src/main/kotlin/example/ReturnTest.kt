package example

import chap2.Person
import chap2.part3.Color

val store = mapOf("beanie" to Person("beanie", 28), "joy" to Person("joy", 25))

fun findPerson(name: String): Person? {
    return store[name]
}

fun example1(name: String): Any {
    // elvis 연산자 뒤의 return은 "example1" 함수의 반환값을 결정
    val personAge = findPerson(name)?.age
        ?: return "Nobody"

    if (personAge > 30) {
        return "늙다리"
    }

    return "젊다리"
}

fun example2(color: Color): Any {
    val result = when (color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> {
            // when의 case block안 return도 마찬가지 전체 outer 함수를 반환
            return "warm"
        }
        Color.GREEN -> {
            "neutral"
        }
        Color.BLUE, Color.INDIGO, Color.VIOLET -> {
            "cold"
        }
    }

    return "result: $result"
}

fun main(args: Array<String>) {
    println(example1("none"))
    println(example2(Color.RED)) // warm
    println(example2(Color.BLUE)) // result: cold
}