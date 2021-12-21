package chap9

import java.util.*

/*
* Star Projection (*)
* List<*> -> 원소 타입이 알려지지 않은 List
* MutableList<*>, MutableList<Any?> 는 같지 않음
* - MutableList<*>: 어떤 정해진 구체적인 타입의 원소만을 담는 리스트지만 그 원소의 타입을 정확히 모른다는 사실 표현
*   - MutableList<*> 타입의 리스트에서 원소를 얻을 수 있다.(get 같은 조회)
*   - 이 때는 원소 타입이 Any?의 하위타입이다.
* - MutableList<Any?>: 모든 타입의 원소를 담을 수 있다는 사실을 표현
* - 코틀린의 "*" 는 자바에서 "?" 와일드카드와 비슷
*/

fun main() {
    val list: MutableList<Any?> = mutableListOf('a', 1, "qwe")
    val chars = mutableListOf('a', 'b', 'c')
    val unknownElements: MutableList<*> =
        if (Random().nextBoolean()) list else chars

    // unknownElements.add(42) -> compile error
    println(unknownElements.first())    // first() 는 Any? 타입의 원소를 반환

    // 여기서 알 수있는 것은 MutableList<*>는 MutableList<out Any?> 처럼 동작한다는 것이다.
}