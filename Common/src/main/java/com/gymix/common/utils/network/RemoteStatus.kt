package com.gymix.common.utils.network

sealed class RemoteStatus<T>(
    val data: T? = null,
    val message: String? = null,
    val state: Boolean = false
) {
    class Success<T>(data: T?) : RemoteStatus<T>(data)
    class Error<T>(message: String?, data: T? = null) : RemoteStatus<T>(data, message = message)
    class Loading<T>(state: Boolean, data: T? = null) : RemoteStatus<T>(data, state = state)
}
