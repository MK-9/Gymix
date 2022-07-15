package com.gymix.data.repository

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.remote.datasource.musicApi.MusicApiDataSource
import com.gymix.domain.entities.spotify.DomainMusicApiRequest
import com.gymix.domain.entities.spotify.DomainMusicApiResponse
import com.gymix.domain.repository.MusicApiRepository
import javax.inject.Inject

class DefaultMusicApiRepository @Inject constructor(
    private val dataSource: MusicApiDataSource
) : MusicApiRepository {

    override suspend fun getTrack(): RemoteStatus<DomainMusicApiResponse> {
        return dataSource.getTrack(DomainMusicApiRequest())
    }

}