package com.gymix.domain.repository

import com.gymix.domain.entity.Foods
import com.gymix.domain.entity.RecipeResponse
import io.reactivex.rxjava3.core.Single

interface FoodForkRepository {
    fun getFoodRecipe(recipeId: String): Single<RecipeResponse>

    fun searchFood(query: String): Single<Foods>
}