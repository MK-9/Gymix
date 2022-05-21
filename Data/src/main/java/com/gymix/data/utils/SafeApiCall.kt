package com.gymix.data.utils

import com.gymix.common.utils.network.RemoteStatus
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(input: suspend () -> Response<T>): RemoteStatus<T> {
    try {
        val response = input.invoke()

        if (response.isSuccessful) {
            if (response.body() != null) {
                return RemoteStatus.Success(response.body())
            }
        }

        return RemoteStatus.Error(response.message())

    } catch (e: Exception) {
        return when (e) {
            is HttpException -> {
                RemoteStatus.Error("HttpException")
            }

            is IOException -> {
                RemoteStatus.Error("IOException")
            }

            else ->
                RemoteStatus.Error("error")
        }
    }
}