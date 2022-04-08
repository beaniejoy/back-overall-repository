package io.beaniejoy.springframeworkbasic

import io.beaniejoy.springframeworkbasic.repository.BookRepository
import io.beaniejoy.springframeworkbasic.repository.BookRepositoryImpl

class BookService(
    private val bookRepository: BookRepository = BookRepositoryImpl()
) {

}