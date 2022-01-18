package com.gymix.domain.di

import com.gymix.domain.interactor.AddNoteInteractor
import com.gymix.domain.interactor.DeleteNoteInteractor
import com.gymix.domain.interactor.GetNoteInteractor
import com.gymix.domain.interactor.GetNotesInteractor
import com.gymix.domain.useCase.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun provideNoteUseCases()

    @Binds
    abstract fun provideGetNoteUseCase(useCase: GetNoteInteractor): GetNoteUseCase

    @Binds
    abstract fun provideAddNoteUseCase(useCase: AddNoteInteractor): AddNoteUseCase

    @Binds
    abstract fun provideDeleteNoteUseCase(useCase: DeleteNoteInteractor): DeleteNoteUseCase

    @Binds
    abstract fun provideGetNotesUseCase(useCase: GetNotesInteractor): GetNotesUseCase
}