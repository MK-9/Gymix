package com.gymix.data.di.module

import com.gymix.data.di.qualifiers.BookRetrofitQualifier
import com.gymix.data.di.qualifiers.SpotifyRetrofitQualifier
import com.gymix.data.remote.api.BookServices
import com.gymix.data.remote.api.SpotifyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiService {

    @Provides
    @Singleton
    fun provideBookService(@BookRetrofitQualifier retrofit: Retrofit): BookServices =
        retrofit.create(BookServices::class.java)

    @Provides
    @Singleton
    fun provideSpotifyService(@SpotifyRetrofitQualifier retrofit: Retrofit): SpotifyService =
        retrofit.create(SpotifyService::class.java)

}