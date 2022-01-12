package com.gymix.domain.interactor

import com.gymix.domain.entity.Note
import com.gymix.domain.repository.NoteRepository
import com.gymix.domain.useCase.GetNotesUseCase
import com.gymix.domain.util.NoteOrder
import com.gymix.domain.util.OrderType
import io.reactivex.rxjava3.core.Single

class GetNotesInteractor(val repository: NoteRepository) : GetNotesUseCase {

    override fun invoke(noteOrder: NoteOrder): Single<List<Note>> {
        repository.getNotes().map { notes ->
            when (noteOrder.orderType) {

                //ascending order
                is OrderType.ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { note -> note.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { note -> note.timeStamp }
                        is NoteOrder.Color -> notes.sortedBy { note -> note.color }
                    }
                }

                //descending order
                is OrderType.descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { note -> note.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { note -> note.timeStamp }
                        is NoteOrder.Color -> notes.sortedByDescending { note -> note.color }
                    }
                }
            }
        }
    }
}