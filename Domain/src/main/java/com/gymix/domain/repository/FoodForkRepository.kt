package com.gymix.domain.repository

import com.gymix.domain.entity.Recipe
import com.gymix.domain.entity.Foods
import com.gymix.domain.entity.Result
import io.reactivex.rxjava3.core.Single

interface FoodForkRepository {
    fun getFoodRecipe(query: String): Single<Result<Recipe>>

    fun searchFood(query: String): Single<Result<Foods>>
}