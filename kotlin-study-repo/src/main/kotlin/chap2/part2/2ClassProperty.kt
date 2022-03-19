package chap2.part2

/*
* 자바는 field, 코틀린은 property 기본 기능 제공
* property: field + accessor method(getter, setter)
*
* 코틀린 기본 가시성은 public
* val: private final String name, getter
* var: private String address, getter, setter
*/
class Person(val name: String, var address: String)

/*
* 커스텀 접근자
* 뒷받침하는 필드(일반적인 저장공간이 있는 필드)가 아닌 그 때 그 때 계산된 값 반환
* 일반적인 getter 와 프로퍼티의 커스텀 getter 의 성능상 차이는 없음
* 보통은 일반적인 getter 로 사용하는 것이 좋아 보임 (isSquare())
*/
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }

//    fun isSquare(): Boolean {
//        return height == width
//    }
}