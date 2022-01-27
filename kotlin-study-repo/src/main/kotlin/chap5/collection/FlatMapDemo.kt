package chap5.collection

class Book(val title: String, val authors: List<String>)
/*
* flatMap
* 1. 인자로 주어진 람다를 컬렉션의 모든 객체에 적용
* 2. 람다를 적용한 결과 얻어지는 여러 리스트를 한 리스트로 모은다.
*
* flatMap 의 반환 타입은 List 다. (헷갈리지 말 것)
*/
fun main() {
    val str = "testpack"
    println(str.toList())

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })

    val books = listOf(
        Book("Book1", listOf("Author A")),
        Book("Book2", listOf("Author A", "Author B")),
        Book("Book3", listOf("Author C", "Author B"))
    )

    println(books.flatMap { it.authors })
    println(books.map { it.authors }.flatten())
}