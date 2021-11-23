package chap6.nullable

/*
* 확장함수 중에서 null 체크를 자체적으로 해주는 것들도 존재
* 이런 확장함수는
* */
fun main() {
    // nullable
    val str: String? = "hello world"

    // nullable 에 대한 안전한 호출 제공
    println(str?.isEmpty())
}