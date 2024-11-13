package com.dicoding.eventdicoding.ui.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.eventdicoding.data.NewsRepository
import kotlinx.coroutines.launch

class SettingViewModel(private val repository: NewsRepository) :ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return repository.getTheme()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            repository.saveTheme(isDarkModeActive)
        }
    }
}