package com.gymix.domain.useCase

import com.gymix.domain.entity.Foods
import com.gymix.domain.entity.Result
import io.reactivex.rxjava3.core.Single

interface SearchFoodUseCase {
    operator fun invoke(title: String): Single<Result<Foods>>
}