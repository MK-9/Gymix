package com.gymix.data

import com.gymix.domain.entity.Note
import io.reactivex.rxjava3.core.Single

interface NoteDataSource {
    fun getNotes(): Single<List<Note>>

    fun getNoteById(id: Int): Note?

    fun insertNote(note: Note)

    fun deleteNote(note: Note)
}