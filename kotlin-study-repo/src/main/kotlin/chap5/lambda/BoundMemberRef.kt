package chap5.lambda

import chap2.Person

fun main() {
    val p = Person("Dmitry", 34)
//    val personAgeFunction = { person: Person -> person.age }
    val personAgeFunction = Person::age
    println(personAgeFunction(p))

    val dAgeFunction = p::age
    println(dAgeFunction())

}