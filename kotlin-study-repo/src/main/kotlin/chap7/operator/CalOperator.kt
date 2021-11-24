package chap7.operator

data class Point(val x: Int, val y: Int) {
    // operator 연산자 사용 (+, +=)
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }
}

// compare 관련 operator (<,>)
class Person(
    val firstName: String, val lastName: String
) : Comparable<Person> {
    // Comparable 의 compareTo 메소드는 operator method 라서 override 라고 하면 된다.
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }
}

fun main() {
    var p1 = Point(10, 20)
    var p2 = Point(30, 40)

    println(p1 + p2) // Point class 에 plus 함수에 operator를 붙여줘야 동작
    p1 += p2
    println(p1)

    val numbers = ArrayList<Int>()
    numbers += 42
    println(numbers[0])

    //-----------------------------------------------
    val person1 = Person("Alice", "Smith")
    val person2 = Person("Bob", "Johnson")

    println(person1 < person2) // false
}