package io.beaniejoy.springframeworkbasic.repository

import io.beaniejoy.springframeworkbasic.entity.Book
import java.math.BigDecimal
import java.time.LocalDateTime

class BookRepositoryImpl: BookRepository {
    companion object {
        val BOOK_STORE: List<Book> = listOf(
            Book(
                id = 1L,
                name = "book_1",
                price = BigDecimal.valueOf(12500L),
                description = "desc_1",
                createdAt = LocalDateTime.now(),
                createdBy = "system"
            ),
            Book(
                id = 2L,
                name = "book_2",
                price = BigDecimal.valueOf(30000L),
                description = "desc_2",
                createdAt = LocalDateTime.now(),
                createdBy = "system"
            ),
            Book(
                id = 3L,
                name = "book_3",
                price = BigDecimal.valueOf(24900),
                description = "desc_3",
                createdAt = LocalDateTime.now(),
                createdBy = "system"
            ),
        )
    }

    override fun findById(id: Long): Book? {
        return BOOK_STORE.find { it.id == id };
    }

    override fun findByName(name: String): Book? {
        return BOOK_STORE.find { it.name == name };
    }
}