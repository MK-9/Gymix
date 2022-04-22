package com.gymix.data.network.di.module

import com.gymix.data.network.di.qualifiers.BaseUrlQualifier
import com.gymix.data.network.di.qualifiers.ReadingTimeQualifier
import com.gymix.data.network.di.qualifiers.WritingTimeQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ApiConfig {

    @BaseUrlQualifier
    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @ReadingTimeQualifier
    @Provides
    fun provideReadingTime(): Long = READING_TIME

    @WritingTimeQualifier
    @Provides
    fun provideWritingTime(): Long = WRITING_TIME

    companion object {
        const val READING_TIME = 2000L
        const val WRITING_TIME = 2000L
        const val BASE_URL = "https://get.taaghche.com/"
//        const val BASE_URL = "https://forkify-api.herokuapp.com/api/"
    }
}