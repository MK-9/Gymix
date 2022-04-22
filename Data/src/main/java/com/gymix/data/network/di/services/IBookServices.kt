package com.gymix.data.network.di.services

import com.gymix.data.network.model.ApiBookResponse
import retrofit2.http.GET

interface IBookServices {

    @GET("v2/everything?filters={\"list\":[{\"type\":3,\"value\":164},{\"type\":21,\"value\":0},{\"type\":50,\"value\":0}]}")
    fun getBooks(): ApiBookResponse

}