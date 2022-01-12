package com.gymix.domain.useCase

import com.gymix.domain.entity.Note

interface GetNoteUseCase {
    operator fun invoke(id: Int): Note?
}