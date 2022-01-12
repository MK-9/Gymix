package com.gymix.domain.interactor

import com.gymix.domain.entity.Note
import com.gymix.domain.repository.NoteRepository
import com.gymix.domain.useCase.DeleteNoteUseCase

class DeleteNoteInteractor(val repository: NoteRepository) : DeleteNoteUseCase {

    override fun invoke(note: Note) {
        repository.deleteNote(note = note)
    }
}