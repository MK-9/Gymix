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
        @BookBaseUrlQualifier baseUrl: String, @BookOkHttpQualifier okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @SpotifyRetrofitQualifier
    @Provides
    fun provideSpotifyRetrofit(
        @SpotifyBaseUrlQualifier baseUrl: String, @SpotifyOkHttpQualifier okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @BookOkHttpQualifier
    @Provides
    fun provideBookOkHttp(
        @ReadingTimeQualifier readTimeout: Long,
        @WritingTimeQualifier writeTimeout: Long,
        @HttpLoggingInterceptorQualifier interceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
        .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
        .callTimeout(writeTimeout, TimeUnit.MILLISECONDS)
        .addInterceptor(interceptor)
        .build()

    @SpotifyOkHttpQualifier
    @Provides
    fun provideSpotifyOkHttp(
        @ReadingTimeQualifier readTimeout: Long,
        @WritingTimeQualifier writeTimeout: Long,
        @HttpLoggingInterceptorQualifier loggingInterceptor: Interceptor,
        @SpotifyInterceptorQualifier spotifyInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
        .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
        .callTimeout(writeTimeout, TimeUnit.MILLISECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(spotifyInterceptor)
        .build()

    @HttpLoggingInterceptorQualifier
    @Provides
    fun provideHttpLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @SpotifyInterceptorQualifier
    @Provides
    fun provideSpotifyInterceptor(): Interceptor =
        SpotifyApiInterceptor()

}