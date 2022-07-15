package com.gymix.data.remote.datasource.musicApi

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.spotify.DomainMusicApiResponse

interface MusicApiDataSource {
    suspend fun getApiToken(header: String, grant_type: String): RemoteStatus<DomainMusicApiResponse>
}