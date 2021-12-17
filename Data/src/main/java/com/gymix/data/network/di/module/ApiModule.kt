package com.gymix.data.network.di.module

import com.gymix.data.network.di.qualifiers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.internal.cache.CacheInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @ForkifyRetrofitQualifier
    @Provides
    fun provideForkifyRetrofit(
        @BaseUrlQualifier baseUrl: String, okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttp(
        @ReadingTimeQualifier readTimeout: Long,
        @WritingTimeQualifier writeTimeout: Long,
        @HttpLoggingInterceptorQualifier interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
            .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
            .callTimeout(writeTimeout, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @HttpLoggingInterceptorQualifier
    @Provides
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

}