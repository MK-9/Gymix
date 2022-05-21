package com.gymix.data.datasource

import com.gymix.common.utils.network.RemoteStatus
import retrofit2.Response
import com.gymix.domain.entities.DomainBookResponse

interface BookDataSource {
    suspend fun fetchBooks(): RemoteStatus<DomainBookResponse>
}