package com.dicoding.eventdicoding.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.eventdicoding.data.NewsRepository
import com.dicoding.eventdicoding.data.local.entity.FavoriteEvent
import com.dicoding.eventdicoding.data.response.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailEventViewModel (private val repository: NewsRepository): ViewModel() {
    private val _eventDetail = MutableLiveData<Event?>()
    val eventDetail: LiveData<Event?> get() = _eventDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getFavoriteById(id: Int): LiveData<FavoriteEvent> {
        return repository.getFavoriteById(id = id)
    }

    fun deleteFavorite(favoriteEvent: FavoriteEvent){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(favoriteEvent)
        }
    }

    fun insertFavorite(favoriteEvent: FavoriteEvent){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(favoriteEvent)
        }
    }

    fun getEventDetail(eventId: Int) {
        viewModelScope.launch {
            val result = repository.getDetailEvent(eventId)
            _eventDetail.postValue(result)
        }
    }
}
