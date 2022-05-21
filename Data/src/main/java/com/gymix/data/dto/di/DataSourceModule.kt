package com.gymix.data.dto.di

import com.gymix.data.dto.BookDataSource
import com.gymix.data.dto.BookDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideBookDataSource(dataSource: BookDataSourceImpl): BookDataSource
}