package com.dicoding.eventdicoding.ui.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.eventdicoding.data.NewsRepository
import com.dicoding.eventdicoding.data.local.entity.FavoriteEvent

class FavoriteViewModel(private val repository: NewsRepository): ViewModel() {
    val isLoading : LiveData<Boolean> get() = repository.isLoading

    fun getAllFavorite(): LiveData<List<FavoriteEvent>> {
        return repository.getAllFavorite()
    }
}