package com.dicoding.myapp_news

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        // Get the views
        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvTitle: TextView = findViewById(R.id.tv_item_title)
        val tvDetail: TextView = findViewById(R.id.tv_item_detail)
        val tvFrom: TextView = findViewById(R.id.tv_item_from)
        val tvWriter: TextView = findViewById(R.id.tv_item_writer)
        val tvRelease: TextView = findViewById(R.id.tv_item_release)

        //
        val dataNews = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<News>(MainActivity.key_news, News::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<News>(MainActivity.key_news)
        }

        if (dataNews != null) {
            tvTitle.text = dataNews.title
            tvDetail.text = dataNews.detail
            tvFrom.text = dataNews.from
            tvWriter.text = dataNews.writer
            tvRelease.text = dataNews.release
            Glide.with(this)
                .load(dataNews.photo)
                .into(imgPhoto)
        }
    }
}
