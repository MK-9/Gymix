package com.gymix.data.repository

import com.gymix.data.NoteDataSource
import com.gymix.domain.entity.Note
import com.gymix.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Single

class DefaultNoteRepository(
    private val noteLocalDataSource: NoteDataSource,
    private val noteRemoteDataSource: NoteDataSource
) : NoteRepository {

    override fun getNotes(): Single<List<Note>> {
        return noteLocalDataSource.getNotes()
    }

    override fun getNoteById(id: Int): Note? {
        return noteLocalDataSource.getNoteById(id = id)
    }

    override fun insertNote(note: Note) {
        noteLocalDataSource.insertNote(note = note)
    }

    override fun deleteNote(note: Note) {
        noteLocalDataSource.deleteNote(note = note)
    }
}