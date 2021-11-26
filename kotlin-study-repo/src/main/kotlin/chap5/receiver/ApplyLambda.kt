package chap5.receiver

import java.lang.StringBuilder

/*
* with 절과 차이점은 apply에 전달된 수신객체를 return 한다는 것
* (with는 따로 return 을 사용자 임의로 정할 수 있다.)
* */
fun applyAlphabet() = StringBuilder().apply {
    for (letter in 'A'..'B') {
        append(letter)
    }
    append("\nThe End!")
}.toString()