package chap4.constructor

// 주생성자가 존재하면 부생성자는 무조건 주생성자에게 직간접적으로 생성을 위임해야 한다.
class Example(val name: String) {
    var age: Int = 20
    var height: Int = 500

//    컴파일 에러!
//    constructor(name: String, age: Int) {
//        this.age = age
//    }

    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }

    constructor(name: String, age: Int, height: Int) : this(name, age) {
        this.height = height
    }
}

// age 에 대해서 default 값 부여
// 코틀린에서는 Example2("beanie") 처럼 한 개 인자를 가진 생성자 사용 가능
// 자바코드에서는 new Example2("beanie") 처럼 name 하나에 대한 생성자 제공 X
class Example2(val name: String, val age: Int = 20)