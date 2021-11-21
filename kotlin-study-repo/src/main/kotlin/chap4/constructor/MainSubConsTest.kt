package chap4.constructor

open class MainPart {
    val number: Int

    constructor(number: Int) {
        this.number = number
    }
}

/*
* 주생성자와 부생성자 같이 사용하는 경우 아래 본문 안에 맴버필드가 var, val 둘다 컴파일 에러
* 주생성자가 있기에 param 1개 짜리 생성자가 생성
* 거기에는 name 에 대한 초기화밖에 없음 -> prop, prop2 초기화 진행 X
* 그렇기 때문에 따로 맴버필드에 초기화 식을 둬서 default 로 설정하지 않는 이상 에러 발생할 수 밖에 없다.
*/
class Part(var name: String) {
    // 둘다 컴파일 에러
//    var prop: Int
//    val prop2: Int
    // 이렇게 초기화 식으로 default 값 설정시 괜찮다.
    var prop3: Int = 1
    // val 은 애초에 초기화식 하는 순간 다른 곳에서 변경 불가능(final 이므로)
    val prop4: Int = 1

    constructor(name: String, prop: Int, prop2: Int): this(name) {
//        this.prop = prop
//        this.prop2 = prop2
    }

    constructor(name: String = "joy", prop3: Int) : this(name) {
        this.name = name
        this.prop3 = prop3
    }
}

fun main() {
    val part = Part(prop3 = 10)
    println("${part.name} / ${part.prop3}")
}