package io.beaniejoy.springframeworkbasic.bean.chap1.sub4

class Outer(
    val name: String,
    val inner: Inner
) {
    class Inner(val address: String)
}