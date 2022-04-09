package io.beaniejoy.springframeworkbasic.bean

import java.math.BigDecimal
import java.util.*

class TestBean3 {
    var id: Long = 0L
    var firstName: String? = null
    var lastName: String? = null
    var price: BigDecimal? = null
    var dateOfBirth: Date? = null
        set(value) {
            println("dateOfBirth setter - ${value}")
            field = value
        }

    constructor() {
        println("default cons")
    }

    override fun toString(): String {
        return "TestBean2(id=$id, firstName=$firstName, lastName=$lastName, price=$price, dateOfBirth=$dateOfBirth)"
    }
}