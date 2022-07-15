package com.gymix.data.di.module

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MusicApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("session","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2NTc4NzM0MTUuMCwic2Vzc2lvbiI6IjY1MTgyNDkzNTkxNjI4OTAiLCJhY2NvdW50SWQiOjEyNTc4ODQsInRyYWNraW5nSWQiOjI4OTg1NTEsImFjY291bnREZXZpY2VJZCI6NTY1OTU0OSwiYXBsIjoiMjYiLCJvcCI6IkFuZHJvaWQifQ.jnB6gbhj9RrpMzjA74dNkhWLaZDrcP7d-uGgCyBuVcK4SozNAnqGpdq7Jig8L0uF4HSOj_WCanmSAdeh_3rypA")
            .addHeader("x-app-name","android-taaghche")
            .addHeader("x-app-version","902")
            .addHeader("x-app-version-name","9.0.2d")
            .build()
        return chain.proceed(request)
    }
}