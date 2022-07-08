package com.gymix.store

import androidx.lifecycle.ViewModel
import com.gymix.common.utils.network.RemoteStatus
import com.gymix.domain.useCase.GetApiTokenUseCase
import com.gymix.domain.useCase.GetBookUseCase
import com.gymix.presentation.book.mappers.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val usecase: GetBookUseCase,
    private val getApiTokenUseCase: GetApiTokenUseCase
) : ViewModel() {

    fun fetchBooks() = flow {
        emit(RemoteStatus.Loading(true))

        delay(1000)

        when (val result = usecase.invoke()) {
            is RemoteStatus.Success -> {
                emit(RemoteStatus.Loading(false))
                emit(RemoteStatus.Success(result.data?.map()))
            }
            is RemoteStatus.Error -> {
                emit(RemoteStatus.Loading(false))
                emit(RemoteStatus.Error(result.message))
            }
        }
    }

    fun getApiToken() = flow {
        emit(RemoteStatus.Loading(true))

        delay(1000)

        when (val result = getApiTokenUseCase.invoke()) {
            is RemoteStatus.Loading -> {
                emit(RemoteStatus.Loading(false))
                emit(RemoteStatus.Success(result.data))
            }

            is RemoteStatus.Error -> {
                emit(RemoteStatus.Loading(false))
                emit(RemoteStatus.Success(result.data))
            }
        }
    }

}