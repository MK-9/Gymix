package com.gymix.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class TestViewModel(val app: Application, val useCases: ClipListUseCases) : AndroidViewModel(app) {
}