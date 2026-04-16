package com.example.mealsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsapp.db.AppDatabase
import com.example.mealsapp.repository.MealRepository
import com.example.mealsapp.viewmodel.MealViewModel

class MealsFragment : Fragment(R.layout.fragment_meals) {
    private lateinit var viewModel: MealViewModel
    private lateinit var adapter: MealAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = AppDatabase.getDb(requireContext())
        val repo = MealRepository(db.dao())

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return MealViewModel(repo) as T
            }
        })[MealViewModel::class.java]

        adapter = MealAdapter {
            startActivity(Intent(requireContext(), DetailActivity::class.java).putExtra("meal", it))
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        val loader = view.findViewById<ProgressBar>(R.id.loader)

        viewModel.meals.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            loader.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadMeals()
    }
}