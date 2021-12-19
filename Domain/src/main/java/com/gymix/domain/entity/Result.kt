package com.gymix.domain.entity

sealed class Result<out T : Any> {
    data class OnSuccess<out T : Any>(val response: T) : Result<T>()
    data class OnError(val message: String?) : Result<Nothing>()
    data class InProgress(val state: Boolean) : Result<Nothing>()
}
