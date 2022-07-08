package com.gymix.domain.interactor

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.spotify.DomainAuthKeyResponse
import com.gymix.domain.repository.SpotifyRepository
import com.gymix.domain.useCase.GetApiTokenUseCase
import javax.inject.Inject

class GetApiTokenInteractor @Inject constructor(
    val repository: SpotifyRepository
) : GetApiTokenUseCase {

    override suspend fun invoke(): RemoteStatus<DomainAuthKeyResponse> {
        return repository.getApiToken()
    }

}