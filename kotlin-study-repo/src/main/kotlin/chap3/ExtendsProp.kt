package chap3

import sun.security.pkcs11.wrapper.CK_C_INITIALIZE_ARGS
import java.lang.StringBuilder

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)
    val map = mapOf<String, String>("one" to "hello", "two" to "hello2")
    println(list.joinToString(separator = "; ", prefix = "(", postfix = ")"))

    for ((key, value) in map) {
        println("${key} / ${value}")
    }
}