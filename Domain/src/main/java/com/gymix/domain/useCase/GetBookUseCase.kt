package com.gymix.domain.useCase

import com.gymix.domain.entities.DomainBook
import com.gymix.common.Result

interface GetBookUseCase {
    suspend operator fun invoke(): Result<List<DomainBook>>
}