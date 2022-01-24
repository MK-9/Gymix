package com.gymix.presentation

import androidx.lifecycle.ViewModel
import com.gymix.domain.useCase.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(val noteUseCases: AddNoteUseCase) : ViewModel() {


}