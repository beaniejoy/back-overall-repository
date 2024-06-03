package com.example.reactiveweb.reactor.demo.error

class ErrorHandlingService {

    fun tenTimesExcept(index: Int, exceptNum: Int): Int {
        if (index == exceptNum) {
            throw RuntimeException("$index 일 때 exception trigger")
        }

        return index * 10
    }
}

val errorHandlingService = ErrorHandlingService()