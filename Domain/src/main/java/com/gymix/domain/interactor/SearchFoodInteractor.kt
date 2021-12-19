package com.gymix.domain.interactor

import com.gymix.domain.entity.Foods
import com.gymix.domain.repository.FoodForkRepository
import com.gymix.domain.useCase.SearchFoodUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchFoodInteractor @Inject constructor(val repository: FoodForkRepository) : SearchFoodUseCase {

    override fun invoke(title: String): Single<Foods> {
        return repository.searchFood(title)
    }
}