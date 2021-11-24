package chap7.collection

import java.time.LocalDate

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    // 익명 객체를 통해 생성
    object : Iterator<LocalDate> {
        var current = start
        override fun hasNext(): Boolean =
            current <= endInclusive

        override fun next(): LocalDate = current.apply {
            current = plusDays(1)
        }
    }

fun main() {
    val newYear = LocalDate.ofYearDay(2022, 1)
    val daysOff = newYear.minusDays(1)..newYear

    for (dayOff in daysOff) {
        println(dayOff)
    }
}