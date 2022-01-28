package com.gymix.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gymix.domain.entity.Note
import com.gymix.domain.useCase.AddNoteUseCase
import com.gymix.domain.useCase.GetNoteUseCase
import com.gymix.domain.useCase.GetNotesUseCase
import com.gymix.domain.util.NoteOrder
import com.gymix.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val getNotesUseCase: GetNotesUseCase) : ViewModel() {

    private lateinit var noteMutableLiveData: MutableLiveData<List<Note>>
    val noteLiveData: LiveData<List<Note>> = noteMutableLiveData

    fun configNotes(noteOrder: NoteOrder = NoteOrder.Date(OrderType.ascending)) {
        getNotesUseCase(noteOrder).subscribe({ notes ->
            noteMutableLiveData.value = notes
        }, { throwable ->

        })
    }

}