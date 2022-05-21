package com.gymix.domain.interactor

import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.entities.DomainBookResponse
import com.gymix.domain.repository.BookRepository
import com.gymix.domain.useCase.GetBookUseCase
import javax.inject.Inject

class GetBookInteractor @Inject constructor(val repository: BookRepository) : GetBookUseCase {

    override suspend fun invoke(): RemoteStatus<DomainBookResponse> {
        return repository.getBook()
    }
}