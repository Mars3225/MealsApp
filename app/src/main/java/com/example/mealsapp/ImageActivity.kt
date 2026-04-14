package com.example.mealsapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil3.load

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val img = ImageView(this)
        setContentView(img)
        img.load(intent.getStringExtra("img"))
    }
}
