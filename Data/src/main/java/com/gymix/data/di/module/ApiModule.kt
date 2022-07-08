package com.gymix.data.di.module

import com.gymix.data.di.qualifiers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @BookRetrofitQualifier
    @Provides
    fun provideBooksRetrofit(
        @BookBaseUrlQualifier baseUrl: String, okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @SpotifyRetrofitQualifier
    @Provides
    fun provideSpotifyRetrofit(
        @SpotifyBaseUrlQualifier baseUrl: String, okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideOkHttp(
        @ReadingTimeQualifier readTimeout: Long,
        @WritingTimeQualifier writeTimeout: Long,
        @HttpLoggingInterceptorQualifier interceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
        .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
        .callTimeout(writeTimeout, TimeUnit.MILLISECONDS)
        .addInterceptor(interceptor)
        .build()

    @HttpLoggingInterceptorQualifier
    @Provides
    fun provideInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

}