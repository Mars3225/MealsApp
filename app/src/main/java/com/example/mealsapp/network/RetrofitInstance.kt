package com.example.mealsapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: MealApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/search.php?s=")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApi::class.java)
    }
}
