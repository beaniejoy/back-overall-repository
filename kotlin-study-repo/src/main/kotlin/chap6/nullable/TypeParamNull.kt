package chap6.nullable

/*
* 코틀린에서 Generic Type Parameter는 기본적으로 nullable 하다.
* 즉 제네릭 타입 파라미터를 사용하는 함수에서는 내부에 null에 대한 처리가 있어야 컴파일 단계를 통과할 수 있다.
*/

fun <T> printHashCode(t: T) {
    // T는 nullable 하기에 안전한 호출을 통해 null 처리를 해야 한다.
    // 여기서 t의 타입은 "Any?" 로 추론됨
    println(t?.hashCode())
}

// 널이 될 수 없는 타입 상한을 지정
fun <T: Any> printHashCodeNotNull(t: T) {
    // null에 대한 처리를 하지 않아도 된다. (nullable 하지 않음을 확정했기에)
    println(t.hashCode())
}
