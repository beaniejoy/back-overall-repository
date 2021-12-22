package chap3

/*
* method param에 default 값을 설정하면 kotlin에서 해당 메서드 사용시 생략 가능하다.
* 인자를 직접 지정해서 사용가능하기에 실제 java에서 생겨나는 overloading method 개수가 많아진다.
* 2^(n-1)개의 오버로딩 메소드가 생길 것이다. (n은 default 값 설정된 파라미터 개수) <- TODO 이부분은 좀 살펴봐야됨
*
* Controller 같은 고유의 메소드가 필수인 곳에서는 사용에 주의해야 한다.
*/
@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

val UNIX_LINE_SEPARATOR = "\n"

fun main() {
//    println(joinToString(listOf(1, 2, 3), prefix = "("))
//    println(joinToString(listOf(1, 2, 3), prefix = "(", postfix = ")", separator = ","))
}