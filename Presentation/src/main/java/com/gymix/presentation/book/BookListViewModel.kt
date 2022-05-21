package com.gymix.presentation.book

import androidx.lifecycle.ViewModel
import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.useCase.GetBookUseCase
import com.gymix.presentation.book.mappers.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(val usecase: GetBookUseCase) : ViewModel() {

    fun fetchBooks() = flow {
        emit(RemoteStatus.Loading(true))
        when (val result = usecase.invoke()) {
            is RemoteStatus.Success -> emit(RemoteStatus.Success(result.data?.map()))
            is RemoteStatus.Error -> emit(RemoteStatus.Error(result.message))
        }
    }

}