package chap2.part2

/*
* 자바는 field, 코틀린은 property 기본 기능 제공
* property: field + accessor method(getter, setter)
*
* 코틀린 기본 가시성은 public
* val: private final String name, getter
* var: private String address, getter, setter
*/
data class Person(val name: String, var address: String)
