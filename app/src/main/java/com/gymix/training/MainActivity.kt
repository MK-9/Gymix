package com.gymix.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.gymix.domain.entity.Result
import com.gymix.presentation.TestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.foodRecipeLiveData
            .observe(this, { result ->
                when (result) {
                    is Result.OnSuccess -> {
                        Log.d("pizza recipe", result.response.title)
                    }

                    is Result.OnError -> {
                        result.message?.let { Log.d("pizza recipe", it) }
                    }

                    is Result.InProgress -> {
                        Log.d("pizza recipe", "" + result.state)
                    }
                }
            })

        viewModel.getFoodRecipe("47746")
    }
}