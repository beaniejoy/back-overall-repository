package io.beaniejoy.springframeworkbasic.repository

import io.beaniejoy.springframeworkbasic.entity.Book

interface BookRepository {
    fun findById(id: Long): Book?
    fun findByName(name: String): Book?
}