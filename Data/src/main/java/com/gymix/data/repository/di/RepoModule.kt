package com.gymix.data.repository.di

import com.gymix.data.repository.DefaultBookRepository
import com.gymix.data.utils.DefaultDispatchersProvider
import com.gymix.data.utils.DispatcherProvider
import com.gymix.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun provideBookRepository(repository: DefaultBookRepository): BookRepository

    @Binds
    abstract fun provideDispatcher(provider: DefaultDispatchersProvider): DispatcherProvider
}