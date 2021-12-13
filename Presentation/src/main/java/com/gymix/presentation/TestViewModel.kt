package com.gymix.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gymix.domain.useCase.ClipListUseCases


class TestViewModel(val app: Application, val useCases: ClipListUseCases) : AndroidViewModel(app) {
}