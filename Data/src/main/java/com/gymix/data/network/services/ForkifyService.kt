package com.gymix.data.network.services

import com.gymix.domain.entity.Foods
import com.gymix.domain.entity.RecipeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ForkifyService {
    @GET("get")
    fun getFoodRecipe(@Query("rId") recipeId: String): Single<RecipeResponse>

    @GET("search")
    fun searchFood(@Query("q") query: String): Single<Foods>
}