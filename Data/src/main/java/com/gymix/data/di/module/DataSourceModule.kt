package com.gymix.data.di.module

import com.gymix.data.remote.datasource.book.BookDataSource
import com.gymix.data.remote.datasource.book.BookDataSourceImpl
import com.gymix.data.remote.datasource.musicApi.MusicApiDataSource
import com.gymix.data.remote.datasource.musicApi.MusicApiDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideBookDataSource(dataSource: BookDataSourceImpl): BookDataSource

    @Binds
    abstract fun provideSpotifySource(dataSource: MusicApiDataSourceImpl): MusicApiDataSource
}