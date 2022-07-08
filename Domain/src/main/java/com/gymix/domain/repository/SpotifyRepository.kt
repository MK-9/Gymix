package com.gymix.domain.repository

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.spotify.DomainAuthKeyRequest
import com.gymix.domain.entities.spotify.DomainAuthKeyResponse

interface SpotifyRepository {
    suspend fun getApiToken(): RemoteStatus<DomainAuthKeyResponse>
}