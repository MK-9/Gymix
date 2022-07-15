package com.gymix.domain.interactor

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.spotify.DomainMusicApiResponse
import com.gymix.domain.repository.MusicApiRepository
import com.gymix.domain.useCase.GetTrackUseCase
import javax.inject.Inject

class GetTrackInteractor @Inject constructor(
    val repository: MusicApiRepository
) : GetTrackUseCase {

    override suspend fun invoke(): RemoteStatus<DomainMusicApiResponse> {
        return repository.getTrack()
    }

}