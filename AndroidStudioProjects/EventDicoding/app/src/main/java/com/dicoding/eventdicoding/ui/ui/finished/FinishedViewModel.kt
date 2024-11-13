package com.dicoding.eventdicoding.ui.ui.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.eventdicoding.data.NewsRepository
import com.dicoding.eventdicoding.data.response.ListEventsItem
import kotlinx.coroutines.launch


class FinishedViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _events = MutableLiveData<List<ListEventsItem>?>()
    val events: LiveData<List<ListEventsItem>?> = _events

    val isLoading: LiveData<Boolean> = repository.isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    companion object {
        private const val TAG = "FinishedViewModel"
    }

    init {
        getFinishedEventsFromApi()
    }

    private fun getFinishedEventsFromApi() {
        viewModelScope.launch {
            val result = repository.getAllFinishedEvent()
            _events.postValue(result)
        }
    }
}
