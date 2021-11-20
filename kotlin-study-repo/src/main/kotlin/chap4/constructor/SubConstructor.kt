package chap4.constructor

// 상속관계에서의 부생성자
open class Human {
    val name: String
    val age: Int

//    constructor() {
//        println("Human default cons1")
//    }

    constructor(name: String) {
        println("Human cons1 ${name}")
        this.name = name
        this.age = 30
    }

    constructor(name: String, age: Int) {
        println("Human cons2 ${name}, ${age}")
        this.name = name
        this.age = age
    }
}

class Beanie: Human {
    val isMarried: Boolean

    constructor(name: String): this(name, 20) {
        println("Beanie cons1 ${name}")
    }

    constructor(name: String, age: Int): this(name, age, true) {
        println("Beanie cons2 ${name}, ${age}")
    }

    // 여기서 super를 생략하면 super 인 Human 클래스의 default 생성자가 있어야 한다.
    // (기본적으로 super()를 만든다.)
    constructor(name: String, age: Int, isMarried: Boolean): super(name, age) {
        println("Beanie cons3 ${name}, ${age}, ${isMarried}")
        this.isMarried = isMarried
    }
}

fun main() {
    val beanie = Beanie("beanie")
    println("result: ${beanie.name}, ${beanie.age}, ${beanie.isMarried}")
}
