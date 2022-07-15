package com.gymix.domain.di

import com.gymix.domain.interactor.*
import com.gymix.domain.useCase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {
    @Binds
    abstract fun provideGetBookUseCase(book: GetBookInteractor): GetBookUseCase

    @Binds
    abstract fun provideGetTrackUseCase(book: GetTrackInteractor): GetTrackUseCase
}