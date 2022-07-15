package com.gymix.domain.entities.spotify

data class DomainMusicApiResponse(
    var id: Int,
    var tracks: List<DomainTrack>? = null,
    var isSubscription: Boolean,
    var type: Int,
    var timestamp: Int,
    var systemNotifications: List<DomainSystemNotification>,
    var parameters: List<Any>? = null,
)