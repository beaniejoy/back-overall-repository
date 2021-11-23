package chap6.nullable

fun strLenNotNull(s: String) = s.length

// paramter가 nullable(?) 하면 해당 함수는 null에 대한 처리를 해줘야 한다.
fun strLenNullable(s: String?) =
    if (s != null) s.length else 0

fun main() {
    // 코틀린은 변수 타입 설정에서 ? 같이 null 체크를 하지 않으면 null을 변수에 담을 수 없다.
    // val name: String = null // (컴파일 에러)

    // 컴파일 에러 (not null parameter에 null 주입)
    // strLenNotNull(null)

    println(strLenNullable(null))

}
