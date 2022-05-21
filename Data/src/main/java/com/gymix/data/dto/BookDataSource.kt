package com.gymix.data.dto

import retrofit2.Response
import com.gymix.domain.entities.DomainBookResponse

interface BookDataSource {
    suspend fun fetchBooks(): Response<DomainBookResponse>
}