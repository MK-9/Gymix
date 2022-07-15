package com.gymix.domain.repository

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.spotify.DomainMusicApiResponse

interface SpotifyRepository {
    suspend fun getApiToken(): RemoteStatus<DomainMusicApiResponse>
}