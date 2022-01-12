package com.gymix.domain.repository

import com.gymix.domain.entity.Note
import io.reactivex.rxjava3.core.Single

interface NoteRepository {
    fun getNotes(): Single<List<Note>>

    fun getNoteById(id: Int): Note?

    fun insertNote(note: Note)

    fun deleteNote(note: Note)
}