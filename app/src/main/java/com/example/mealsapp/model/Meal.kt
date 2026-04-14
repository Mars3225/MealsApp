package com.example.mealsapp.model

import java.io.Serializable

data class Meal(
    val idMeal: Int,
    val strMeal: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String?
) : Serializable
