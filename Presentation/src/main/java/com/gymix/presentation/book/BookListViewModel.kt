package com.gymix.presentation.book

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymix.common.Result
import com.gymix.domain.entities.DomainBook
import com.gymix.domain.useCase.GetBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(val usecase: GetBookUseCase) : ViewModel() {

    private val _stateFlow = MutableStateFlow<Result<List<DomainBook>>>(Result.InProgress(false))
    var stateFlow = _stateFlow.asStateFlow()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable -> handleError(throwable) }

    fun fetchBook() {
        viewModelScope.launch(exceptionHandler) {
            _stateFlow.value = Result.InProgress(true)

            getBook()

            _stateFlow.value = Result.InProgress(false)
        }
    }

    private fun handleError(throwable: Throwable) {
        throwable.message?.let { Log.d("aaa", it) }
    }

    private suspend fun getBook() {
        coroutineScope {
            _stateFlow.value = usecase.invoke()
        }
    }

}