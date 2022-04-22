package com.gymix.domain.repository

import com.gymix.domain.entity.Book

interface BookRepository {
    suspend fun getBook(): List<Book>
}