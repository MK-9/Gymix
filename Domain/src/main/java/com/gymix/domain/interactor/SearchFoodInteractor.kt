package com.gymix.domain.interactor

import com.gymix.domain.entity.Foods
import com.gymix.domain.entity.Result
import com.gymix.domain.repository.FoodForkRepository
import com.gymix.domain.useCase.SearchFoodUseCase
import io.reactivex.rxjava3.core.Single

class SearchFoodInteractor constructor(val repo: FoodForkRepository) : SearchFoodUseCase {

    override fun invoke(title: String): Single<Result<Foods>> {
        return repo.searchFood(title)
    }
}