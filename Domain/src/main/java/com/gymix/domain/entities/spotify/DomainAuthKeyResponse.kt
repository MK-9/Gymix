package com.gymix.domain.entities.spotify

data class DomainAuthKeyResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Long
//    val scope: String
)
