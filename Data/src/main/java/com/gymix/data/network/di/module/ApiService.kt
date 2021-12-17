package com.gymix.data.network.di.module

import com.gymix.data.network.di.qualifiers.ForkifyRetrofitQualifier
import com.gymix.data.network.services.ForkifyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ApiService {
    @Provides
    fun provideForkifyService(@ForkifyRetrofitQualifier retrofit: Retrofit): ForkifyService {
        return retrofit.create(ForkifyService::class.java)
    }
}