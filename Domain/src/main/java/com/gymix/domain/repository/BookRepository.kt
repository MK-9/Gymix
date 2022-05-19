package com.gymix.domain.repository

import com.gymix.common.Result
import com.gymix.domain.entity.Book

interface BookRepository {
    suspend fun getBook(): Result<List<Book>>
}