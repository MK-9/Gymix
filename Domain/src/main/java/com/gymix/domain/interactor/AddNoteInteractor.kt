package com.gymix.domain.interactor

import com.gymix.domain.entity.InvalidNoteException
import com.gymix.domain.entity.Note
import com.gymix.domain.repository.NoteRepository
import com.gymix.domain.useCase.AddNoteUseCase
import javax.inject.Inject

class AddNoteInteractor @Inject constructor(val repository: NoteRepository) : AddNoteUseCase {

    override fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("title is blank")
        }

        if (note.content.isBlank()) {
            throw InvalidNoteException("content is blank")
        }

        repository.insertNote(note = note)
    }
}