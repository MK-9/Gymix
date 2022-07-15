package com.gymix.domain.entities.spotify

data class DomainMusicApiRequest(
    val bookId: Int = 89859,
    val foreign: Boolean = false,
    val refId: String = "70710e64-9615-43be-aae2-edb476b08979.7",
    val suppressIab: Boolean = false,
    val type: Int = 0,
    val themeId: Int = 1,
    val session: String = ""
)
