package com.dicoding.eventdicoding.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.eventdicoding.R
import com.dicoding.eventdicoding.data.local.entity.FavoriteEvent
import com.dicoding.eventdicoding.ui.detail.DetailEvent

class FavoriteAdapter  : ListAdapter<FavoriteEvent, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventCoverImage: ImageView = itemView.findViewById(R.id.eventCoverImage)
        val eventName: TextView = itemView.findViewById(R.id.eventName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val event = getItem(position)
        holder.eventName.text = event.name ?: "No Title"

        // Load cover image using Glide
        Glide.with(holder.itemView.context)
            .load(event.mediaCover)
            .into(holder.eventCoverImage)

        holder.itemView.setOnClickListener {
            val toIntent = Intent (it.context, DetailEvent::class.java)
            toIntent.putExtra("EVENT_ID", event.id)
            it.context.startActivity(toIntent)
        }
    }


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteEvent> = object : DiffUtil.ItemCallback<FavoriteEvent>() {
            override fun areItemsTheSame(
                oldItem: FavoriteEvent,
                newItem: FavoriteEvent
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteEvent,
                newItem: FavoriteEvent
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}