package com.gymix.data.network.di.module

import com.gymix.data.network.di.qualifiers.BookRetrofitQualifier
import com.gymix.data.network.di.services.IBookServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiService {

    @Provides
    @Singleton
    fun provideBookService(@BookRetrofitQualifier retrofit: Retrofit): IBookServices =
        retrofit.create(IBookServices::class.java)

}