package com.gymix.data.repository

import com.gymix.data.dto.BookDataSource
import com.gymix.domain.entity.Book
import com.gymix.domain.repository.BookRepository

class DefaultBookRepository(private val dataSource: BookDataSource) : BookRepository {

    override suspend fun getBook(): List<Book> {
        return dataSource.fetchBooks().bookList
    }
}