package com.gymix.domain.useCase

import com.gymix.domain.entity.Note

interface AddNoteUseCase {
    operator fun invoke(note: Note)
}