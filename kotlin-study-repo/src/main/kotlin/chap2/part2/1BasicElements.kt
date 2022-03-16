package chap2.part2

/*
* System.out.println(...) -> println(...)
* 코틀린 라이브러리는 표준 자바 라이브러리 함수를 간결하게 사용할 수 있는 Wrapper 제공
* 자바 함수와 간혹 겹칠 때가 존재한다. IDE 자동추천으로 import 가 잘못될 수 있음에 주의
*/
fun main(args: Array<String>) {
    println("Hello, world!")
}

/*
* if, when, try...catch 등은 문(statement)이 아니고 식(expression)
* 문과 식의 결정적 차이는 *값*을 만들어낼 수 있는지 차이
*/
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

/*
* - 식이 본문인 함수
* 코틀린은 정적 타입 지정 언어이지만 코틀린 컴파일러가 분석해 자동으로 반환타입 지정
* (타입 추론, type inference)
* 블록이 본문인 함수는 값을 반환할 때 반드시 return 사용해야 함
*/
fun maxEx(a: Int, b: Int): Int = if (a > b) a else b
fun printSumEx(a: Int, b: Int) = println(a + b);

/*
* - 변수
* 코틀린 컴파일러는 초기화 식을 분석해서 타입을 변수 타입으로 알아서 지정해준다. (타입 추론)
*/
fun variables() {
    val question = "hello kotlin world!"
    val answer = 30
    val answer2: Int = 30

    // 초기화 식을 분리하면 반드시 변수 타입 지정해야 함 (타입 추론 불가)
    val answer3: Int
    answer3 = 30

    /*
    * val: immutable 변수 (변경 불가능한 참조변수, java final)
    * var: mutable 변수 (변경 가능한 참조변수)
    */
    var answer4 = 40
}

/*
* - 문자열 템플릿
* 웬만하면 중괄호를 붙여서 사용하는 것이 좋다.
* 한글, 영문 혼용해서 사용할 때도 그렇고 변수명 자체가 오염되어 unresolved reference 발생 가능
* $name -> ${name}
* 컴파일러는 StringBuilder 사용하는 바이트코드를 생성
* (append 로 이어붙이는 형태, java String "+" 연산도 마찬가지)
*/
fun stringTemplate(message: String?) {
    val name = "Beanie"
    println("Hello ${name}")
    // 이런 식으로 중괄호 식안에 여러 코드를 넣을 수도 있다.
    println("Hello, ${if (message.isNullOrEmpty().not()) message else "default message"}!")
}