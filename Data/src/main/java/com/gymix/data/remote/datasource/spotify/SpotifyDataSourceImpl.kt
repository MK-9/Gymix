package com.gymix.data.remote.datasource.spotify

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.remote.api.SpotifyApi
import com.gymix.data.utils.DispatcherProvider
import com.gymix.data.utils.safeApiCall
import com.gymix.domain.entities.spotify.DomainAuthKeyRequest
import com.gymix.domain.entities.spotify.DomainAuthKeyResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SpotifyDataSourceImpl @Inject constructor(
    private val api: SpotifyApi,
    private val dispatcher: DispatcherProvider
) : SpotifyDataSource {

    override suspend fun getApiToken(
        header: String,
        grant_type: String
    ): RemoteStatus<DomainAuthKeyResponse> = withContext(dispatcher.io()) {
        safeApiCall { api.getAuthKey(header, grant_type) }
    }

}