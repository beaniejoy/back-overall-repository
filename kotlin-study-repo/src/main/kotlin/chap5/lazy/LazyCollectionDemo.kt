package chap5.lazy

import chap2.Person

fun main() {
    val people = listOf(Person("p1", 20), Person("p2", 35))

    people.asSequence()
        .map(Person::name)
        .filter { it.startsWith("A") }
        .toList()
}