package com.gymix.data.repository.di

import com.gymix.data.dto.BookDataSource
import com.gymix.data.dto.BookDataSourceImpl
import com.gymix.data.repository.DefaultBookRepository
import com.gymix.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideBookRepository(bookDataSource: BookDataSource): BookRepository {
        return DefaultBookRepository(bookDataSource)
    }

}