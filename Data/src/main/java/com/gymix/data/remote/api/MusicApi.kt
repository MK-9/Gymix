package com.gymix.data.remote.api

import com.gymix.domain.entities.spotify.DomainMusicApiRequest
import com.gymix.domain.entities.spotify.DomainMusicApiResponse
import retrofit2.Response
import retrofit2.http.*

interface MusicApi {
    @POST("order/book")
    suspend fun getTrack(@Body request: DomainMusicApiRequest): Response<DomainMusicApiResponse>
}