package com.example.reactiveweb.demo.error

class ErrorHandlingService {

    fun tenTimesExcept(index: Int, exceptNum: Int): Int {
        if (index == 2) {
            throw RuntimeException("$index 일 때 exception trigger")
        }

        return index * 10
    }
}

val errorHandlingService = ErrorHandlingService()