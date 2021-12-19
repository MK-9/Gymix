package com.gymix.domain.entity

data class Recipe(
    val publisher: String,
    val title: String,
    val ingredients: List<String>,
    val source_url: String,
    val recipe_id: String,
    val image_url: String,
    val social_rank: Long,
    val publisher_url: String
)
