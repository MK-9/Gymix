package com.gymix.data.repository

import com.gymix.data.network.services.ForkifyService
import com.gymix.domain.entity.Foods
import com.gymix.domain.entity.RecipeResponse
import com.gymix.domain.repository.FoodForkRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DefaultFoodForkRepository @Inject constructor(private val service: ForkifyService) :
    FoodForkRepository {

    override fun getFoodRecipe(recipeId: String): Single<RecipeResponse> {
        return service.getFoodRecipe(recipeId)
    }

    override fun searchFood(query: String): Single<Foods> {
        return service.searchFood(query)
    }
}