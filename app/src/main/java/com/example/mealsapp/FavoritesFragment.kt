package com.example.mealsapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsapp.db.AppDatabase
import com.example.mealsapp.repository.MealRepository

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = AppDatabase.getDb(requireContext())
        val repo = MealRepository(db.dao())

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)

        repo.getFavorites().observe(viewLifecycleOwner) {
            recycler.adapter = FavoriteAdapter(it)
        }
    }
}
