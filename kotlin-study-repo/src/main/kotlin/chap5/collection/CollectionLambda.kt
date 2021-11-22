package chap5.collection

import chap2.Person

fun main() {
    val people = listOf(Person("Beanie", 30), Person("Joy", 27))

    people.filter { it.age == people.maxByOrNull(Person::age)!!.age }
}