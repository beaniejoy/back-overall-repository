package io.beaniejoy.springframeworkbasic.service

import io.beaniejoy.springframeworkbasic.entity.Book
import io.beaniejoy.springframeworkbasic.repository.BookRepository

class BookService(
    private val bookRepository: BookRepository
) {
    fun findBookById(id: Long): Book {
        return bookRepository.findById(id)
            ?: throw RuntimeException("해당 id(${id})는 없는 Book id입니다.")
    }
}