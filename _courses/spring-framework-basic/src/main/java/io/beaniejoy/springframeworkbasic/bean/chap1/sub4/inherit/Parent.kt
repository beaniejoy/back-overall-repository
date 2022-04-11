package io.beaniejoy.springframeworkbasic.bean.chap1.sub4.inherit

class Parent(
    val name: String,
    val age: Int,
) {
    override fun toString(): String {
        return "Parent(name='$name', age=$age)"
    }
}