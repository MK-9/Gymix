package com.gymix.domain.useCase

import com.gymix.domain.entity.Note

interface DeleteNoteUseCase {
    operator fun invoke(note: Note)
}