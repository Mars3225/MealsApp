package com.example.mealsapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil3.load
import com.example.mealsapp.databinding.ActivityDetailBinding
import com.example.mealsapp.db.AppDatabase
import com.example.mealsapp.model.Meal
import com.example.mealsapp.repository.MealRepository
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(b.root)

        val meal = intent.getSerializableExtra("meal") as Meal

        val repo = MealRepository(AppDatabase.getDb(this).dao())

        b.title.text = meal.strMeal
        b.area.text = meal.strArea
        b.instructions.text = meal.strInstructions
        b.image.load(meal.strMealThumb)

        val btn = b.btnFav

        lifecycleScope.launch {
            update(btn, repo, meal)
        }

        btn.setOnClickListener {
            lifecycleScope.launch {
                if (repo.isFavorite(meal.idMeal)) repo.removeFavorite(meal)
                else repo.addFavorite(meal)
                update(btn, repo, meal)
            }
        }
    }

    private suspend fun update(btn: Button, repo: MealRepository, meal: Meal) {
        btn.text = if (repo.isFavorite(meal.idMeal))
            "Удалить из избранного" else "Добавить в избранное"
    }
}
