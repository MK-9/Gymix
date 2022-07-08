package com.gymix.data.remote.api

import com.gymix.domain.entities.DomainBookResponse
import retrofit2.Response
import retrofit2.http.GET

interface BookServices {
    @GET("v2/everything?filters={\"list\":[{\"type\":3,\"value\":164},{\"type\":21,\"value\":0},{\"type\":50,\"value\":0}]}")
    suspend fun getBooks(): Response<DomainBookResponse>
}