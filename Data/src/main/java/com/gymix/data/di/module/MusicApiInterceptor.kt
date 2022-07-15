package com.gymix.data.di.module

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

const val Session =
    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2NTc4NzQzMzAuMCwic2Vzc2lvbiI6IjY1MTgyNDkzNTkxNjI4OTAiLCJhY2NvdW50SWQiOjEyNTc4ODQsInRyYWNraW5nSWQiOjI4OTg1NTEsImFjY291bnREZXZpY2VJZCI6NTY1OTU0OSwiYXBsIjoiMjYiLCJvcCI6IkFuZHJvaWQifQ.UlH7PWika24-cUmgo_5Qfu1cGdw9gg_FGUuS4sE-fwb0CBz9YSzRQ3lPzsnlGb7_Q8FTngjoglKFYJincMrGow"

class MusicApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("session", Session)
            .addHeader("x-app-name", "android-taaghche")
            .addHeader("x-app-version", "902")
            .addHeader("x-app-version-name", "9.0.2d")
            .build()
        return chain.proceed(request)
    }
}