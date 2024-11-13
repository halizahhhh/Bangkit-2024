package com.dicoding.myapp_news

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AboutPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_page)

        val fullNameTextView: TextView = findViewById(R.id.tv_full_name)
        val emailTextView: TextView = findViewById(R.id.tv_email)
        val imageView: ImageView = findViewById(R.id.img_about)

        fullNameTextView.text = getString(R.string.full_name)
        emailTextView.text = getString(R.string.email)
        imageView.setImageResource(R.drawable.my_self)
    }
}