package com.example.mealsapp.repository

import com.example.mealsapp.db.FavoriteDao
import com.example.mealsapp.db.FavoriteEntity
import com.example.mealsapp.model.Meal
import com.example.mealsapp.network.RetrofitInstance

class MealRepository(private val dao: FavoriteDao) {

    suspend fun getMeals(): List<Meal> =
        RetrofitInstance.api.getMeals().meals

    suspend fun addFavorite(meal: Meal) {
        dao.insert(FavoriteEntity(meal.idMeal, meal.strMeal, meal.strMealThumb))
    }

    suspend fun removeFavorite(meal: Meal) {
        dao.delete(FavoriteEntity(meal.idMeal, meal.strMeal, meal.strMealThumb))
    }

    fun getFavorites() = dao.getAll()

    suspend fun isFavorite(id: Int) = dao.isFavorite(id)
}