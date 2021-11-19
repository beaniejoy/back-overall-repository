package chap3

import java.lang.StringBuilder

infix fun String.appendAndPrint(other: String): String {
    val strB = StringBuilder(this)
    return strB.append(other).toString()
}