package com.gymix.data.database

import com.gymix.data.NoteDataSource
import com.gymix.domain.entity.Note
import io.reactivex.rxjava3.core.Single

class NoteLocalDataSource(private val noteDao: NoteDao) : NoteDataSource {

    override fun getNotes(): Single<List<Note>> {
        return noteDao.getNotes()
    }

    override fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id = id)
    }

    override fun insertNote(note: Note) {
        noteDao.insertNote(note = note)
    }

    override fun deleteNote(note: Note) {
        noteDao.deleteNote(note = note)
    }
}