package com.gymix.domain.entity

import com.google.gson.annotations.SerializedName

data class RecipeResponse(@SerializedName("recipe") val recipe: Recipe)