package com.gymix.data.remote.datasource.book

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.remote.api.BookApi
import com.gymix.data.utils.DispatcherProvider
import com.gymix.data.utils.safeApiCall
import com.gymix.domain.entities.DomainBookResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(
    private val service: BookApi,
    private val dispatcher: DispatcherProvider
) : BookDataSource {

    /**
     * Fetches the latest books from the network and returns the result.
     * This executes on an IO-optimized thread pool, the function is main-safe.
     */
    override suspend fun fetchBooks(): RemoteStatus<DomainBookResponse> =
        withContext(dispatcher.io()) {
            safeApiCall {
                service.getBooks()
            }
        }

}