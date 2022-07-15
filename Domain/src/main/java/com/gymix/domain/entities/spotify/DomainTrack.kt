package com.gymix.domain.entities.spotify

data class DomainTrack(
    var id: Int,
    var link: String? = null,
    var title: String? = null,
    var duration: Int,
    var size: Int,
    var sequenceNo: Int,
    var type: Int,
)