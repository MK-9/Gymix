package com.gymix.data.remote.api

import com.gymix.domain.entities.spotify.DomainAuthKeyRequest
import com.gymix.domain.entities.spotify.DomainAuthKeyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SpotifyService {
    @POST("token")
    suspend fun getAuthKey(
        @Header("Authorization:Basic") header: String,
        @Body request: DomainAuthKeyRequest
    ): Response<DomainAuthKeyResponse>
}