package chap2

// 이 자체만으로 default constructor가 생성되지는 않는다.
// private final 필드, getter, all args constructor가 생성
class Person(
    val name: String,
    val age: Int
) {
    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}


// 위의 생성되는 부분에서 hashcode & equals, toString, copy가 추가로 생성
data class Company(
    val name: String,
    val part: String,
    val compNo: Long
)