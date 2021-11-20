package chap4.constructor

open class RemoteController {
    val company: String
    val level: Int

    constructor(_company: String) {
        company = _company
        level = 1
    }

    constructor(_company: String, _level: Int) {
        company = _company
        level = _level
    }
}

fun main(args: Array<String>) {
    val rc = RemoteController("beanie_comp")
    println("${rc.company} ${rc.level}")
}