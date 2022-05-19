package com.gymix.domain.interactor

import com.gymix.common.Result
import com.gymix.domain.entity.Book
import com.gymix.domain.repository.BookRepository
import com.gymix.domain.useCase.GetBookUseCase
import javax.inject.Inject

class GetBookInteractor @Inject constructor(val repository: BookRepository) : GetBookUseCase {

    override suspend fun invoke(): Result<List<Book>> {
        return repository.getBook()
    }
}