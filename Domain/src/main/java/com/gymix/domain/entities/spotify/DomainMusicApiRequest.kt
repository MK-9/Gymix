package com.gymix.domain.entities.spotify

data class DomainMusicApiRequest(
    val bookId: Int,
    val foreign: Boolean,
    val refId: Boolean,
    val suppressIab: Boolean,
    val type: Int,
    val session: String
)
