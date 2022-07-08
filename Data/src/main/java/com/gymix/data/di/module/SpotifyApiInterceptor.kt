package com.gymix.data.di.module

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class SpotifyApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
//            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build()
        return chain.proceed(request)
    }
}