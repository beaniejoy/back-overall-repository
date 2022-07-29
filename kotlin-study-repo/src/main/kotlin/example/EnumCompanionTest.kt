package example

import example.LangConverter.Companion.convertLang
import java.util.Locale

enum class HelloStrategy(
    val hello: String
) {
    KOREAN(convertLang(Locale.KOREAN)),
    ENGLISH(convertLang(Locale.ENGLISH)),
    JAPANESE(convertLang(Locale.JAPANESE))
    ;
}

class LangConverter{
    companion object {
        fun convertLang(locale: Locale): String {
            return when (locale) {
                Locale.KOREAN -> "안녕하세요"
                Locale.JAPANESE -> "おはよう。"
                Locale.ENGLISH -> "hello"
                else -> "no lang"
            }
        }
    }
}

fun main() {
    println(HelloStrategy.ENGLISH)
    println(HelloStrategy.KOREAN.hello)
    println(HelloStrategy.JAPANESE.hello)
}