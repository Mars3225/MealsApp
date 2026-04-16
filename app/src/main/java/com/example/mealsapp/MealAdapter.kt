package com.example.mealsapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.mealsapp.databinding.ItemMealBinding
import com.example.mealsapp.model.Meal

class MealAdapter(private val onClick: (Meal) -> Unit) : RecyclerView.Adapter<MealAdapter.VH>() {
    private var list = listOf<Meal>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(l: List<Meal>) {
        list = l
        notifyDataSetChanged()
    }

    inner class VH(val b: ItemMealBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(meal: Meal) {
            b.title.text = meal.strMeal
            b.image.load(meal.strMealThumb)
            b.root.setOnClickListener { onClick(meal) }
        }
    }

    override fun onCreateViewHolder(p: ViewGroup, v: Int) =
        VH(ItemMealBinding.inflate(LayoutInflater.from(p.context), p, false))
    override fun getItemCount() = list.size
    override fun onBindViewHolder(h: VH, i: Int) = h.bind(list[i])
}