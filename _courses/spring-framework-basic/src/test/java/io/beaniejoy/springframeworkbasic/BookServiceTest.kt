package io.beaniejoy.springframeworkbasic

import io.beaniejoy.springframeworkbasic.repository.BookRepository
import io.beaniejoy.springframeworkbasic.repository.BookRepositoryImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BookServiceTest {

    private val bookRepository: BookRepository = BookRepositoryImpl()

    @Test
    fun testService() {
        println(bookRepository.findById(4L))
    }
}