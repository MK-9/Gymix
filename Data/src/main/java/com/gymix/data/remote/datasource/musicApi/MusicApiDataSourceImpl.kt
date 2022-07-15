package com.gymix.data.remote.datasource.musicApi

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.remote.api.MusicApi
import com.gymix.data.utils.DispatcherProvider
import com.gymix.data.utils.safeApiCall
import com.gymix.domain.entities.spotify.DomainMusicApiRequest
import com.gymix.domain.entities.spotify.DomainMusicApiResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicApiDataSourceImpl @Inject constructor(
    private val api: MusicApi,
    private val dispatcher: DispatcherProvider
) : MusicApiDataSource {

    override suspend fun getTrack(request: DomainMusicApiRequest): RemoteStatus<DomainMusicApiResponse> =
        withContext(dispatcher.io()) {
            safeApiCall { api.getTrack(request) }
        }

}