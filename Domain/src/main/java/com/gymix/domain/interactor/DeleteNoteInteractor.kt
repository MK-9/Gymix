package com.gymix.domain.interactor

import com.gymix.domain.entity.Note
import com.gymix.domain.repository.NoteRepository
import com.gymix.domain.useCase.DeleteNoteUseCase
import javax.inject.Inject

class DeleteNoteInteractor @Inject constructor(val repository: NoteRepository) : DeleteNoteUseCase {

    override fun invoke(note: Note) {
        repository.deleteNote(note = note)
    }
}