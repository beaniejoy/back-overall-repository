package testpack

enum class EnumValues {
    A, B, C
}

class TempObj(
    val name: String
)

fun main() {
    val list = EnumValues.values().map { TempObj(it.name) }.toList()

    val a = EnumValues.A
    val a2 = EnumValues.A

    println(a == a2)

    list.forEach { println(it.name) }
}