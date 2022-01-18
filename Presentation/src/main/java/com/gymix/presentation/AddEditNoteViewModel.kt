package com.gymix.presentation

import androidx.lifecycle.ViewModel
import com.gymix.domain.useCase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AddEditNoteViewModel(val noteUseCases: NoteUseCases) : ViewModel() {







}