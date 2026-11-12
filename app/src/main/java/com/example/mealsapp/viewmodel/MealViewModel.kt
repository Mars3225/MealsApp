package com.example.mealsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.R
import com.example.mealsapp.model.Meal
import com.example.mealsapp.repository.MealRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class MealViewModel(private val repo: MealRepository) : ViewModel() {
    private val _meals = MutableLiveData<List<Meal>> ()
    val meals: LiveData<List<Meal>> = _meals

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessageRes = MutableLiveData<Int?>(null)
    val errorMessageRes: LiveData<Int?> = _errorMessageRes

    init {
        loadMeals()
    }

    fun loadMeals() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessageRes.value = null
            try {
                _meals.value = repo.getMeals()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG,"Failed to load meals", e)
                _errorMessageRes.value = R.string.error_load_meals
            } finally {
                _isLoading.value = false
            }
        }
    }

    private companion object {
        const val TAG = "MealViewModel"
    }
}