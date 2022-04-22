package com.gymix.data.dto

import com.gymix.data.network.di.services.IBookServices
import com.gymix.data.network.model.ApiBookResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookDataSource @Inject constructor(
    private val service: IBookServices,
    private val dispatcher: CoroutineDispatcher
) {

    /**
     * Fetches the latest books from the network and returns the result.
     * This executes on an IO-optimized thread pool, the function is main-safe.
     */
    suspend fun fetchBooks(): ApiBookResponse {
        return withContext(dispatcher) {
            service.getBooks()
        }
    }

}