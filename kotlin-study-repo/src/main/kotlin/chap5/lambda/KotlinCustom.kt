package chap5.lambda

import chap2.Person

/*
* 코틀린에서는 함수 호출 시 맨 뒤에 있는 인자가 람다 식이면
* 그 람다를 괄호 밖으로 빼낼 수 있다는 문법 관습이 있다.
*/
fun main() {
    val people = listOf(Person("Beanie", 30), Person("Joy", 27))
    println(people.maxByOrNull { it.age })

    println(people.maxByOrNull(Person::age))

    println(people.maxByOrNull({ p: Person -> p.age }))
    println(people.maxByOrNull { p: Person -> p.age })

    println(
        people.joinToString(separator = " ", transform = { p: Person -> p.name})
    )

    println(
        people.joinToString(separator = " ") { p: Person -> p.name }
    )

    println(
        people.joinToString(separator = " ") { it.name }
    )

    println(
        people.joinToString(separator = " ", transform = Person::name)
    )
}
