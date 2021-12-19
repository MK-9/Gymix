package com.gymix.domain.di

import com.gymix.domain.interactor.GetFoodRecipeInteractor
import com.gymix.domain.interactor.SearchFoodInteractor
import com.gymix.domain.useCase.GetFoodRecipeUseCase
import com.gymix.domain.useCase.SearchFoodUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetFoodRecipeUseCase(useCase: GetFoodRecipeInteractor): GetFoodRecipeUseCase

    @Binds
    abstract fun bindSearchFoodUseCase(useCase: SearchFoodInteractor): SearchFoodUseCase
}