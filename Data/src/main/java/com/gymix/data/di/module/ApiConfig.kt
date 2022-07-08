package com.gymix.data.di.module

import com.gymix.data.di.qualifiers.BookBaseUrlQualifier
import com.gymix.data.di.qualifiers.ReadingTimeQualifier
import com.gymix.data.di.qualifiers.SpotifyBaseUrlQualifier
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

    @SpotifyBaseUrlQualifier
    @Provides
    fun provideSpotifyBaseUrl(): String = SPOTIFY_BASE_URL

    @ReadingTimeQualifier
    @Provides
    fun provideReadingTime(): Long = READING_TIME

    @WritingTimeQualifier
    @Provides
    fun provideWritingTime(): Long = WRITING_TIME

    companion object {
        const val READING_TIME = 2000L
        const val WRITING_TIME = 2000L
        const val BOOK_BASE_URL = "https://get.taaghche.com/"
        const val SPOTIFY_BASE_URL = "https://get.taaghche.com/"
    }
}