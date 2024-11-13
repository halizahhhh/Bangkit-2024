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
import com.dicoding.eventdicoding.data.response.ListEventsItem
import com.dicoding.eventdicoding.ui.detail.DetailEvent

class EventAdapter() : ListAdapter<ListEventsItem, EventAdapter.EventViewHolder>(DIFF_CALLBACK) {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventCoverImage: ImageView = itemView.findViewById(R.id.eventCoverImage)
        val eventName: TextView = itemView.findViewById(R.id.eventName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.eventName.text = event.name ?: "No Title"

        // Load cover image using Glide
        Glide.with(holder.itemView.context)
            .load(event.mediaCover)
            .into(holder.eventCoverImage)

        // Set click listener for each item
        holder.itemView.setOnClickListener {
            val toIntent = Intent (it.context, DetailEvent::class.java)
            toIntent.putExtra("EVENT_ID", event.id)
            it.context.startActivity(toIntent)
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ListEventsItem> = object : DiffUtil.ItemCallback<ListEventsItem>() {
            override fun areItemsTheSame(
                oldItem: ListEventsItem,
                newItem: ListEventsItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ListEventsItem,
                newItem: ListEventsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
