package com.gymix.data.repository.di

import com.gymix.data.NoteDataSource
import com.gymix.data.repository.DefaultNoteRepository
import com.gymix.domain.repository.NoteRepository
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
    fun provideNoteRepository(
        noteLocalDataSource: NoteDataSource,
        noteRemoteDataSource: NoteDataSource
    ): NoteRepository {
        return DefaultNoteRepository(noteLocalDataSource, noteRemoteDataSource)
    }

}