package com.gymix.domain.useCase

data class NoteUseCases(
    val getNote: GetNoteUseCase,
    val addNote: AddNoteUseCase,
    val deleteNote: DeleteNoteUseCase,
    val getNotes: GetNotesUseCase
)
