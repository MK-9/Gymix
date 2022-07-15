package com.gymix.domain.data

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.spotify.DomainMusicApiResponse
import com.gymix.domain.entities.spotify.DomainSystemNotification
import com.gymix.domain.entities.spotify.DomainTrack
import com.gymix.domain.repository.MusicApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FakeTrackRepository : MusicApiRepository {

    //tracks
    private val tracks = listOf(
        DomainTrack(
            id = 501108,
            link = "https://download.taaghche.com/download/Xhlon7LNvqm05rDfQGRTy88eEMHy9zPC",
            title = "نمونه",
            duration = 312,
            size = 4997858,
            sequenceNo = 0,
            type = 9
        )
    )

    //systemNotifications
    private val systemNotifications = arrayListOf(
        DomainSystemNotification(
            additionalData = "عملیات با موفقیت انجام شد",
            status = 0
        )
    )

    //domainMusicApiResponse
    private val domainMusicApiResponse = DomainMusicApiResponse(
        tracks = tracks,
        parameters = arrayListOf(),
        id = 0,
        isSubscription = false,
        type = 9,
        timestamp = 0,
        systemNotifications = systemNotifications
    )


    override suspend fun getTrack(): RemoteStatus<DomainMusicApiResponse> {
        return withContext(Dispatchers.IO) {
            RemoteStatus.Success(domainMusicApiResponse)
        }
    }
}