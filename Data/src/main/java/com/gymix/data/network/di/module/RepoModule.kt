package com.gymix.data.network.di.module

import com.gymix.data.repository.DefaultFoodForkRepository
import com.gymix.domain.repository.FoodForkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {
    @Binds
    abstract fun bindFoodForkifyRepository(repository: DefaultFoodForkRepository): FoodForkRepository
}