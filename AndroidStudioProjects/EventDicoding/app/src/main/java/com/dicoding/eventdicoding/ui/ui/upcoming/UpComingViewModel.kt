package com.dicoding.eventdicoding.ui.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.eventdicoding.data.NewsRepository
import com.dicoding.eventdicoding.data.response.ListEventsItem
import kotlinx.coroutines.launch

class UpComingViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _events = MutableLiveData<List<ListEventsItem>?>()
    val events: LiveData<List<ListEventsItem>?> = _events

    val isLoading: LiveData<Boolean> = repository.isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    companion object {
        private const val TAG = "UpComingViewModel"
    }

    init {
        getUpcomingEventsFromApi()
    }

    private fun getUpcomingEventsFromApi() {
        viewModelScope.launch {
            val result = repository.getAllUpcomingEvent()
            _events.postValue(result)
        }
    }
}
