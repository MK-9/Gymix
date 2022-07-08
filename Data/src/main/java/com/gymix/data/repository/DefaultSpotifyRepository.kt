package com.gymix.data.repository

import android.util.Base64
import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.remote.datasource.spotify.SpotifyDataSource
import com.gymix.data.utils.DispatcherProvider
import com.gymix.domain.entities.spotify.DomainAuthKeyRequest
import com.gymix.domain.entities.spotify.DomainAuthKeyResponse
import com.gymix.domain.repository.SpotifyRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultSpotifyRepository @Inject constructor(
    private val dataSource: SpotifyDataSource
) : SpotifyRepository {

    override suspend fun getApiToken(): RemoteStatus<DomainAuthKeyResponse> {
        val request = DomainAuthKeyRequest("client_credentials")
        val header = Base64.encodeToString("upfc".toByteArray(), Base64.DEFAULT)
        return dataSource.getApiToken(header, request)
    }
}