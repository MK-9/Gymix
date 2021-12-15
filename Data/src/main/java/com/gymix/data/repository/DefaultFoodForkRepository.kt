package com.gymix.data.repository

import com.gymix.domain.entity.Foods
import com.gymix.domain.entity.Recipe
import com.gymix.domain.entity.Result
import com.gymix.domain.repository.FoodForkRepository
import io.reactivex.rxjava3.core.Single

class DefaultFoodForkRepository : FoodForkRepository {
    override fun getFoodRecipe(query: String): Single<Result<Recipe>> {
        TODO("Not yet implemented")
    }

    override fun searchFood(query: String): Single<Result<Foods>> {
        TODO("Not yet implemented")
    }
}