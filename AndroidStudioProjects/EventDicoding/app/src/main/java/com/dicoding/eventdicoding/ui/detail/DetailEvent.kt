package com.dicoding.eventdicoding.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.dicoding.eventdicoding.R
import com.dicoding.eventdicoding.data.local.entity.FavoriteEvent
import com.dicoding.eventdicoding.databinding.ActivityDetailEventBinding
import com.dicoding.eventdicoding.ui.ViewModelFactory

class DetailEvent : AppCompatActivity() {
    private lateinit var binding: ActivityDetailEventBinding
   private val detailEventViewModel by viewModels<DetailEventViewModel> {
       ViewModelFactory.getInstance(this)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val eventId = intent.getIntExtra("EVENT_ID", 0)
        detailEventViewModel.getEventDetail(eventId)

        detailEventViewModel.eventDetail.observe(this) { event ->
            event?.let {

                binding.tvTitle.text = event.name
                binding.tvOwner.text = "Organizer: ${event.ownerName}"
                binding.tvTime.text = "Event Time: ${event.beginTime}"
                binding.tvQuota.text = "Remaining Quota: ${event.quota - event.registrants}"
                binding.tvDescription.text = HtmlCompat.fromHtml(
                    event.description ?: "",
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )


                Glide.with(this)
                    .load(it.mediaCover)
                    .into(binding.eventImage)

                detailEventViewModel.getFavoriteById(event.id).observe(this@DetailEvent) {favorite ->
                    favorite?.let { item ->
                        binding.btnFavorite.setImageResource(R.drawable.ic_favoritefull)
                        binding.btnFavorite.setOnClickListener{
                            detailEventViewModel.deleteFavorite(item)
                        }
                    } ?: run {
                        binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
                        binding.btnFavorite.setOnClickListener {
                            val favoriteItem = FavoriteEvent(
                                id = event.id,
                                name = event.name,
                                mediaCover = event.mediaCover,
                                summary = event.summary
                            )
                            detailEventViewModel.insertFavorite(favoriteEvent = favoriteItem)
                        }
                    }
                }


                binding.btnLink.setOnClickListener {
                    openBrowser(event.link)
                }
            }
        }
        detailEventViewModel.errorMessage.observe(this) { error ->
            error?.let {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openBrowser(url: String?) {
        url?.let {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(intent)
        }
    }
}
