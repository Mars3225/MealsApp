package com.example.mealsapp.viewmodel

import androidx.lifecycle.*
import coil3.util.CoilUtils.result
import com.example.mealsapp.model.Meal
import com.example.mealsapp.repository.MealRepository
import kotlinx.coroutines.launch

class MealViewModel(private val repo: MealRepository) : ViewModel() {
    val meals = MutableLiveData<List<Meal>>()
    val error = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun loadMeals() {
        viewModelScope.launch {
            isLoading.value = true

            try {
                meals.value = repo.getMeals()
                println("ANIME" + "${meals.value}")
            } catch (e: Exception) {
                error.value = "Ошибка загрузки"
            }

            isLoading.value = false
        }
    }
}