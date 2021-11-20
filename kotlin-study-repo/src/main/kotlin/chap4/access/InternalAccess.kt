package chap4.access

import chap4.inherit.Focusable

internal open class TalkativeButton: Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")

    fun callModule() = println("call TalkativeButton in the same module!")
}

/*
* giveSpeech 확장 함수는 기본적으로 public으로 노출 -> internal로 변경 or 수신타입을 public으로 높여야 함
* yell은 private 이라 같은 클래스 안에서만 확인 가능
*
* */
//fun TalkativeButton.giveSpeech() {
//    yell()
//    whisper()
//}