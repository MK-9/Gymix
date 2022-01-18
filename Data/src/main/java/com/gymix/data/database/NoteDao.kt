package com.gymix.data.database

import androidx.room.*
import com.gymix.domain.entity.Note
import io.reactivex.rxjava3.core.Single

@Dao
interface NoteDao {
    @Query("select* from note")
    fun getNotes(): Single<List<Note>>

    @Query("select* from note where id = :id")
    fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}