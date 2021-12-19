package com.gymix.domain.useCase

import com.gymix.domain.entity.RecipeResponse
import io.reactivex.rxjava3.core.Single

interface GetFoodRecipeUseCase {
    operator fun invoke(query: String): Single<RecipeResponse>
}