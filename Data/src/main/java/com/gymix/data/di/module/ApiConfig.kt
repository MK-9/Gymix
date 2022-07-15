package com.gymix.data.di.module

import com.gymix.data.di.qualifiers.BookBaseUrlQualifier
import com.gymix.data.di.qualifiers.ReadingTimeQualifier
import com.gymix.data.di.qualifiers.MusicApiBaseUrlQualifier
import com.gymix.data.di.qualifiers.WritingTimeQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ApiConfig {

    @BookBaseUrlQualifier
    @Provides
    fun provideBookBaseUrl(): String = BOOK_BASE_URL

    @MusicApiBaseUrlQualifier
    @Provides
    fun provideMusicApiBaseUrl(): String = MUSIC_API_BASE_URL

    @ReadingTimeQualifier
    @Provides
    fun provideReadingTime(): Long = READING_TIME

    @WritingTimeQualifier
    @Provides
    fun provideWritingTime(): Long = WRITING_TIME

    companion object {
        const val READING_TIME = 10000L
        const val WRITING_TIME = 10000L
        const val BOOK_BASE_URL = "https://get.taaghche.com/"
        const val MUSIC_API_BASE_URL = "https://api.taaghche.com/mybook/v2/order/book"
    }
}