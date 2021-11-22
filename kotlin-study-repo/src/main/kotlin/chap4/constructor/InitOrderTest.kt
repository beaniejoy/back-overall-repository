package chap4.constructor

/*
* Init 과 Constructor 실행 순서관련 테스트
* 클래스의 객체 생성시 init 블록 호출 -> 해당 생성자 constructor 호출
* 상속받은 경우 1. 상위 클래스의 init 블록, constructor 먼저 호출 후 -> 2. 하위 클래스 호출 함
*
* 주 생성자에 대해서 init 블록이 발동되는 것을 확인
*/
open class Member(_name: String) {
    val name: String = _name
    var age: Int = 20

    init {
        println("Member Class Init Call!")
    }

    constructor(name: String, age: Int) : this(name) {
        this.age = age
        println("Member constructor Call!")
    }
}

class Joy(name: String, age: Int) : Member(name, age) {
    var isMarried: Boolean = false

    init {
        println("Joy Class Init Call!")
    }

    constructor(name: String, age: Int, isMarried: Boolean) : this(name, age) {
        this.isMarried = isMarried
        println("Joy constructor Call!")
    }
}

// 주 생성자가 없어도 init 블록은 실행
class BeanieJoy {
    init {
        println("BeanieJoy Class Init Call!")
    }
}

fun main() {
    /*
    * Member Class Init Call!
    * Member constructor Call!
    * Joy Class Init Call!
    * Joy constructor Call!
    */
    val joy = Joy("joy", 25, true)

    val beanie = BeanieJoy()
}