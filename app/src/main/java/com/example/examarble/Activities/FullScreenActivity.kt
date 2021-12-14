package com.example.examarble.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.example.examarble.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_full_screen.*

@AndroidEntryPoint
class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        val imgPath = intent.getStringExtra("imgPath")
        Glide.with(this).load(imgPath).into(image)
        close.setOnClickListener {
            finish()
        }
    }
}