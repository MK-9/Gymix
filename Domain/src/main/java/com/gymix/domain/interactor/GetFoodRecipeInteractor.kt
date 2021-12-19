package com.gymix.domain.interactor

import com.gymix.domain.entity.RecipeResponse
import com.gymix.domain.repository.FoodForkRepository
import com.gymix.domain.useCase.GetFoodRecipeUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetFoodRecipeInteractor @Inject constructor(private val repository: FoodForkRepository) :
    GetFoodRecipeUseCase {

    override fun invoke(query: String): Single<RecipeResponse> {
        return repository.getFoodRecipe(query)
    }
}