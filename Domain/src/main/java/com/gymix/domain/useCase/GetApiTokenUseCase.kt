package com.gymix.domain.useCase

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.spotify.DomainAuthKeyResponse

interface GetApiTokenUseCase {
    suspend operator fun invoke(): RemoteStatus<DomainAuthKeyResponse>
}