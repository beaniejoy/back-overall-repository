package io.beaniejoy.springframeworkbasic.bean

class TestBean(
    var id: Long,
    var name: String
) {
    constructor() : this(0L, "default name") {
        println("default constructor")
    }
}