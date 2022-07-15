package com.gymix.data.repository

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.remote.datasource.musicApi.MusicApiDataSource
import com.gymix.domain.entities.spotify.DomainMusicApiRequest
import com.gymix.domain.entities.spotify.DomainMusicApiResponse
import com.gymix.domain.repository.SpotifyRepository
import javax.inject.Inject

class DefaultSpotifyRepository @Inject constructor(
    private val dataSource: MusicApiDataSource
) : SpotifyRepository {

    override suspend fun getApiToken(): RemoteStatus<DomainMusicApiResponse> {
        return dataSource.getTrack(DomainMusicApiRequest())
    }

}