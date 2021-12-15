package com.gymix.domain.useCase

import com.gymix.domain.entity.Recipe
import com.gymix.domain.entity.Result
import io.reactivex.rxjava3.core.Single

interface GetFoodRecipeUseCase {
    operator fun invoke(query: String): Single<Result<Recipe>>
}