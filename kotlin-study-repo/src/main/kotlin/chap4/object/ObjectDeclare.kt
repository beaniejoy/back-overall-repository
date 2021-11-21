package chap4.`object`

import chap2.Person

object Payroll {
    val allEmployees = arrayListOf<Person>()

    fun calculateSalary() {
        //...
        println("calculate salary")
    }
}

fun main() {
    Payroll.calculateSalary()
    // Java 에서는 Payroll.INSTANCE.allEmployees 이런 식으로 접근해야 함
    // public static final Payroll INSTANCE;
}