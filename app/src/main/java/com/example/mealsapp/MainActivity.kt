package com.example.mealsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mealsapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pager = findViewById<ViewPager2>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabs)

        pager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Meals"
                else -> "Favorites"
            }
        }.attach()

    }

    class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount() = 2
        override fun createFragment(position: Int): Fragment =
            if (position == 0) MealsFragment() else FavoritesFragment()
    }
}
