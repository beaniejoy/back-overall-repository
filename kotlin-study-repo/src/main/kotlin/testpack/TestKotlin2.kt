package testpack

class Temp(
    _name: String,
    _address: String
) {
    var name: String = _name
        get() = "hello ${field}!!"

    var address: String = _address

    override fun toString(): String {
        return "Temp(name='$name')"
    }
}

data class Temp2(
    val name: String,
    private val address: String
) {
    override fun toString(): String {
        return "Temp(name='$name')"
    }
}

class Temp3(
    _name: String,
    _address: String
) {
    var name: String = _name
    var address: String = _address
}

fun main() {
    val temp = Temp("beanie", "address")
    temp.name = "joy"

    val list: List<Temp> = listOf()

    println(temp)
    println(list.map { it.name }.size)

}