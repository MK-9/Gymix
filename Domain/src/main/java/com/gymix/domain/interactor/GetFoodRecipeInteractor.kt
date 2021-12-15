package com.gymix.domain.interactor

import com.gymix.domain.entity.Recipe
import com.gymix.domain.entity.Result
import com.gymix.domain.repository.FoodForkRepository
import com.gymix.domain.useCase.GetFoodRecipeUseCase
import io.reactivex.rxjava3.core.Single

class GetFoodRecipeInteractor constructor(val repo:FoodForkRepository) : GetFoodRecipeUseCase {

    override fun invoke(query: String): Single<Result<Recipe>> {
        return repo.getFoodRecipe(query)
    }
}