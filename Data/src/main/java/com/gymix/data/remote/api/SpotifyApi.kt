package com.gymix.data.remote.api

import com.gymix.domain.entities.spotify.DomainAuthKeyResponse
import retrofit2.Response
import retrofit2.http.*

interface SpotifyApi {

    @FormUrlEncoded
    @POST("api/token")
    suspend fun getAuthKey(
        @Header("Authorization") header: String,
        @Field("grant_type") grant_type: String
    ): Response<DomainAuthKeyResponse>
}