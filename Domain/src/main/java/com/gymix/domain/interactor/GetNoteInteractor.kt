package com.gymix.domain.interactor

import com.gymix.domain.entity.Note
import com.gymix.domain.repository.NoteRepository
import com.gymix.domain.useCase.GetNoteUseCase

class GetNoteInteractor(val repository: NoteRepository) : GetNoteUseCase {

    override fun invoke(id: Int): Note? {
        return repository.getNoteById(id = id)
    }
}