package com.gymix.domain.useCase

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.DomainBookResponse

interface GetBookUseCase {
    suspend operator fun invoke(): RemoteStatus<DomainBookResponse>
}