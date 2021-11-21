package chap4.access

// sealed 는 기본적으로 open
// sealed 의 외부 클래스는 private 생성자를 가진다. (하위 클래스에서만 호출 가능)
// sealed interface 정의할 수 없다.
// (interface 를 자바에서 구현 못하게 막을 수 있는 방법이 코틀린 컴파일러에게 없다.)
sealed class Expr {
    class Num(val value: Int): Expr()
    class Sum(val left: Expr, val right: Expr): Expr()
    // 또 다른 하위 클래스를 추가하면 아래 when 절에서 컴파일 에러
    // class Mul(val left: Expr, val right: Expr): Expr()
}

fun eval(e: Expr): Int =
    when (e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
    }