package com.gymix.data.remote.datasource.book

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.DomainBookResponse

interface BookDataSource {
    suspend fun fetchBooks(): RemoteStatus<DomainBookResponse>
}