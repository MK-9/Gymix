package com.gymix.data.remote.api

import com.gymix.domain.entities.spotify.DomainMusicApiResponse
import retrofit2.Response
import retrofit2.http.*

interface MusicApi {

    @FormUrlEncoded
    @POST("api/token")
    suspend fun getAuthKey(
        @Header("Authorization") header: String,
        @Field("grant_type") grant_type: String
    ): Response<DomainMusicApiResponse>
}