package com.gymix.data.datasource

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.network.di.services.IBookServices
import com.gymix.data.utils.safeApiCall
import com.gymix.domain.entities.DomainBookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(
    private val service: IBookServices
) : BookDataSource {
//    private val dispatcher: CoroutineDispatcher
//) {

//    /**
//     * Fetches the latest books from the network and returns the result.
//     * This executes on an IO-optimized thread pool, the function is main-safe.
//     */
//    override suspend fun fetchBooks(): RemoteStatus<DomainBookResponse> {
//        return withContext(Dispatchers.IO) {
//            service.getBooks()
//        }
//        return service.getBooks()
//    }

    override suspend fun fetchBooks(): RemoteStatus<DomainBookResponse> = safeApiCall {
        service.getBooks()
    }

}