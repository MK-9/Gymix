package com.gymix.data.remote.datasource.spotify

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.remote.api.SpotifyService
import com.gymix.data.utils.DispatcherProvider
import com.gymix.data.utils.safeApiCall
import com.gymix.domain.entities.spotify.DomainAuthKeyRequest
import com.gymix.domain.entities.spotify.DomainAuthKeyResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SpotifyDataSourceImpl @Inject constructor(
    private val service: SpotifyService,
    private val dispatcher: DispatcherProvider
) : SpotifyDataSource {

    override suspend fun getApiToken(
        header: String,
        request: DomainAuthKeyRequest
    ): RemoteStatus<DomainAuthKeyResponse> = withContext(dispatcher.io()) {
        safeApiCall { service.getAuthKey(header, request) }
    }

}