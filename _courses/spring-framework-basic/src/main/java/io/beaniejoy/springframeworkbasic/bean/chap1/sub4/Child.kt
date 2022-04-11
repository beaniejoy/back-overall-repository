package io.beaniejoy.springframeworkbasic.bean.chap1.sub4

class Child(
    val name: String,
    val address: String,
    val parent: Parent
) {
    override fun toString(): String {
        return "Child(name='$name', address='$address', parent=$parent)"
    }
}