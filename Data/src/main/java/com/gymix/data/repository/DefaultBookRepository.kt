package com.gymix.data.repository

import com.gymix.domain.repository.BookRepository
import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.datasource.BookDataSource
import com.gymix.data.utils.DispatcherProvider
import com.gymix.domain.entities.DomainBookResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultBookRepository @Inject constructor(
    private val dataSource: BookDataSource,
    private val dispatcherProvider: DispatcherProvider
) : BookRepository {

    override suspend fun getBook(): RemoteStatus<DomainBookResponse> {
        return withContext(dispatcherProvider.io()){
            dataSource.fetchBooks()
        }
    }

//    override suspend fun toggleBookMark(bookId: Int): Flow<Set<String>> {
//        return
//    }
}