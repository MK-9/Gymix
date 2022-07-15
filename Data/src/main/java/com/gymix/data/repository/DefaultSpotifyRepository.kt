package com.gymix.data.repository

import android.util.Base64
import com.gymix.common.utils.network.RemoteStatus
import com.gymix.data.remote.datasource.musicApi.MusicApiDataSource
import com.gymix.domain.entities.spotify.DomainMusicApiResponse
import com.gymix.domain.repository.SpotifyRepository
import javax.inject.Inject

class DefaultSpotifyRepository @Inject constructor(
    private val dataSource: MusicApiDataSource
) : SpotifyRepository {

    override suspend fun getApiToken(): RemoteStatus<DomainMusicApiResponse> {
        val header =
            Base64.encodeToString("$Client_ID:$Client_Secret".toByteArray(), Base64.NO_WRAP)
        return dataSource.getApiToken("Basic $header", Grant_Type)
    }

    companion object {
        const val Grant_Type = "client_credentials"
        const val Client_ID = "1b7a48bed103499db68d8d27fb7abee0"
        const val Client_Secret = "096249f329cb4e699703afe31a9f9a48"
    }
}