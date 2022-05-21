package com.gymix.data.repository

import com.gymix.domain.repository.BookRepository
import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.dto.BookDataSource
import com.gymix.data.utils.safeApiCall
import com.gymix.domain.entities.DomainBookResponse

class DefaultBookRepository(private val dataSource: BookDataSource) : BookRepository {

    override suspend fun getBook(): RemoteStatus<DomainBookResponse> = safeApiCall {
       dataSource.fetchBooks()
    }

//    override suspend fun toggleBookMark(bookId: Int): Flow<Set<String>> {
//        return
//    }
}