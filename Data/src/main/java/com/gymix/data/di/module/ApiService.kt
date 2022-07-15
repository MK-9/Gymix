package com.gymix.data.di.module

import com.gymix.data.di.qualifiers.BookRetrofitQualifier
import com.gymix.data.di.qualifiers.MusicApiRetrofitQualifier
import com.gymix.data.remote.api.BookApi
import com.gymix.data.remote.api.MusicApi
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
    fun provideBookService(@BookRetrofitQualifier retrofit: Retrofit): BookApi =
        retrofit.create(BookApi::class.java)

    @Provides
    @Singleton
    fun provideMusicApiService(@MusicApiRetrofitQualifier retrofit: Retrofit): MusicApi =
        retrofit.create(MusicApi::class.java)

}