package chap4.constructor

/*
* 주생성자는 클래스 이름 뒤에 오는 괄호로 둘러싸인 코드가 주 생성자
* 프로퍼티 초기화 식이나 init 블록 안에서만 주생성자 파라미터를 참조할 수 있다.
*/

class Company(val name: String)

// class CompanyCon(_name: String) 과 같다.
class CompanyCon constructor(_name: String) {
    val name: String

    init {
        // init 블록 안에서는 주생성자 파라미터 참조 가능
        name = _name
    }
}

class CompanyVarInit(_name: String, _part: String) {
    val name: String
    val part = _part    // 프로퍼티 초기화식에서 주생성자 파라미터 참조가능

    init {
        println("first init")
        name = _name
        // part = "mobility"
    }

    // 여러 개 init block 생성 가능
    // init 블록을 합쳐서 주생성자 생성될 때 같이 실행
    init {
        println("second init")
    }
}

class CompanyVarCon {
    val name: String
    val part: String

    constructor(_name: String) {
        name = _name
        this.part = "mobility"
    }

    constructor(_name: String, _part: String) {
        name = _name
        part = _part
    }

    init {
        println("init")
        // 컴파일 에러(constructor 파라미터에서는 참조불가)
        // name = _name
    }
}

fun main(args: Array<String>) {
    // first init
    // second init
    val comp = CompanyVarInit("beanie_comp", "mobility")
}