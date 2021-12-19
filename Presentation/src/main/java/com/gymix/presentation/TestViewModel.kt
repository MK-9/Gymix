package com.gymix.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gymix.domain.entity.Recipe
import com.gymix.domain.entity.RecipeResponse
import com.gymix.domain.entity.Result
import com.gymix.domain.useCase.GetFoodRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getFoodRecipeUseCase: GetFoodRecipeUseCase
) : ViewModel() {

    private var _foodRecipeLiveData = MutableLiveData<Result<Recipe>>()
    val foodRecipeLiveData: LiveData<Result<Recipe>> = _foodRecipeLiveData

    fun getFoodRecipe(query: String) {
        startProgress()

        getFoodRecipeUseCase(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ recipe ->
                fetchData(recipe)

                finishProgress()

            }, {
                finishProgress()

                handleError("Error")
            })
    }

    private fun startProgress() {
        _foodRecipeLiveData.value = Result.InProgress(true)
    }

    private fun finishProgress() {
        _foodRecipeLiveData.value = Result.InProgress(false)
    }

    private fun fetchData(response: RecipeResponse) {
        _foodRecipeLiveData.value = Result.OnSuccess(response.recipe)
    }

    private fun handleError(msg: String) {
        _foodRecipeLiveData.value = Result.OnError(msg)
    }

    override fun onCleared() {
        super.onCleared()
    }
}