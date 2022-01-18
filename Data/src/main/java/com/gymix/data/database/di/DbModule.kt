package com.gymix.data.database.di

import android.app.Application
import androidx.room.Room
import com.gymix.data.NoteDataSource
import com.gymix.data.database.NoteDatabase
import com.gymix.data.database.NoteLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(database: NoteDatabase): NoteDataSource {
        return NoteLocalDataSource(database.noteDao)
    }

}