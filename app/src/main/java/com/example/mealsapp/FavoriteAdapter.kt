package com.example.mealsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.mealsapp.databinding.ItemMealBinding
import com.example.mealsapp.db.FavoriteEntity

class FavoriteAdapter(private val list: List<FavoriteEntity>) :
    RecyclerView.Adapter<FavoriteAdapter.VH>() {
    class VH(val b: ItemMealBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(item: FavoriteEntity) {
            b.title.text = item.name
            b.image.load(item.image)
        }
    }

    override fun onCreateViewHolder(p: ViewGroup, v: Int) =
        VH(ItemMealBinding.inflate(LayoutInflater.from(p.context), p, false))
    override fun getItemCount() = list.size
    override fun onBindViewHolder(h: VH, i: Int) = h.bind(list[i])
}