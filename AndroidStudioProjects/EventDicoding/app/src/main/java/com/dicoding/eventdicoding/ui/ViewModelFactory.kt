package com.dicoding.eventdicoding.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.eventdicoding.data.Injection
import com.dicoding.eventdicoding.data.NewsRepository
import com.dicoding.eventdicoding.ui.detail.DetailEventViewModel
import com.dicoding.eventdicoding.ui.ui.favorite.FavoriteViewModel
import com.dicoding.eventdicoding.ui.ui.finished.FinishedViewModel
import com.dicoding.eventdicoding.ui.ui.setting.SettingViewModel
import com.dicoding.eventdicoding.ui.ui.upcoming.UpComingViewModel

class ViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UpComingViewModel::class.java)-> {
                UpComingViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FinishedViewModel::class.java) -> {
                FinishedViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailEventViewModel::class.java) -> {
                DetailEventViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SettingViewModel::class.java) -> {
                SettingViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Uknown ViewModel Class " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}