package com.gymix.data.datasource.di

import com.gymix.data.datasource.BookDataSource
import com.gymix.data.datasource.BookDataSourceImpl
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