package com.gymix.domain.repository

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.spotify.DomainMusicApiResponse

interface MusicApiRepository {
    suspend fun getTrack(): RemoteStatus<DomainMusicApiResponse>
}