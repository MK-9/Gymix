package com.gymix.domain.useCase

import com.gymix.domain.entity.Book
import com.gymix.common.Result

interface GetBookUseCase {
    suspend operator fun invoke(): Result<List<Book>>
}