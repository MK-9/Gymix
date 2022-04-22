package com.gymix.data.network.di.module

import com.gymix.data.network.di.qualifiers.BookRetrofitQualifier
import com.gymix.data.network.di.services.IBookServices
import com.gymix.data.network.model.ApiBookResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.http.GET

@InstallIn(SingletonComponent::class)
@Module
interface ApiService {

    @Provides
    fun provideBookService(@BookRetrofitQualifier retrofit: Retrofit): IBookServices =
        retrofit.create(IBookServices::class.java)

}