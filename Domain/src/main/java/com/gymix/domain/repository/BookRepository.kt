package com.gymix.domain.repository

import com.gymix.common.Result
import com.gymix.domain.entities.DomainBook

interface BookRepository {
    suspend fun getBook(): Result<List<DomainBook>>

//    suspend fun toggleBookMark(bookId: Int): Flow<Set<String>>
}