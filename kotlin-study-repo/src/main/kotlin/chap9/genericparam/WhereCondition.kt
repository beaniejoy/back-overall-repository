package chap9.genericparam

import java.lang.Appendable

fun <T> ensureTrailingPeriod(seq: T) where T : CharSequence, T : Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}

fun main() {
    val hello = StringBuilder("Hello World")
    ensureTrailingPeriod(hello)
    println(hello)
}