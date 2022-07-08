package com.gymix.data.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

//    @Provides
//    @Singleton
//    fun provideRoomDatabase(app: Application): NoteDatabase {
//        return Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideNoteDataSource(database: NoteDatabase): NoteDataSource {
//        return NoteLocalDataSource(database.noteDao)
//    }

}