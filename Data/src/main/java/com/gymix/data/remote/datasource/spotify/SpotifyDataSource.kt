package com.gymix.data.remote.datasource.spotify

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.spotify.DomainAuthKeyRequest
import com.gymix.domain.entities.spotify.DomainAuthKeyResponse

interface SpotifyDataSource {
    suspend fun getApiToken(header:String, request: DomainAuthKeyRequest): RemoteStatus<DomainAuthKeyResponse>
}