package com.example.mealsapp.network

import com.example.mealsapp.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("search.php")
    suspend fun getMeals(@Query("s") s: String = ""): MealResponse
}