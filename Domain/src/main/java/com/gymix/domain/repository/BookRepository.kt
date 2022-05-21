package com.gymix.domain.repository

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.DomainBookResponse

interface BookRepository {
    suspend fun getBook(): RemoteStatus<DomainBookResponse>

//    suspend fun toggleBookMark(bookId: Int): Flow<Set<String>>
}