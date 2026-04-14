package com.example.mealsapp.viewmodel

import androidx.lifecycle.*
import coil3.util.CoilUtils.result
import com.example.mealsapp.model.Meal
import com.example.mealsapp.repository.MealRepository
import kotlinx.coroutines.launch

class MealViewModel(private val repo: MealRepository) : ViewModel() {

    val meals = MutableLiveData<List<Meal>>()
    val error = MutableLiveData<String>()

     fun loadMeals() {
        viewModelScope.launch {
            try {
                val result = repo.getMeals()
                println("ANIME: $result") // 👈 ВАЖНО
                meals.value = result
            } catch (e: Exception) {
                error.value = "Ошибка загрузки"
            }
        }
    }
}
