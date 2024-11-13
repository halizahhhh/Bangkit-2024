package com.dicoding.eventdicoding.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.dicoding.eventdicoding.data.local.entity.FavoriteEvent
import com.dicoding.eventdicoding.data.local.room.FavoriteDao
import com.dicoding.eventdicoding.data.response.Event
import com.dicoding.eventdicoding.data.response.ListEventsItem
import com.dicoding.eventdicoding.data.retrofit.ApiService
import com.dicoding.eventdicoding.ui.ui.setting.SettingPreferences
import kotlinx.coroutines.delay
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NewsRepository private constructor(
    private val apiService: ApiService,
    private val favoriteDao: FavoriteDao,
    private val preferences: SettingPreferences
){
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getTheme(): LiveData<Boolean> {return preferences.getThemeSetting().asLiveData()}

    suspend fun saveTheme(isDarkMode: Boolean) = preferences.saveThemeSetting(isDarkMode)

    fun getFavoriteById(id: Int): LiveData<FavoriteEvent> = favoriteDao.getFavoriteById(id)

    fun insertFavorite(favoriteEvent: FavoriteEvent) = executorService.execute { favoriteDao.insertFavorite(favoriteEvent) }

    fun deleteFavorite(favoriteEvent: FavoriteEvent) = executorService.execute { favoriteDao.deleteFavorite(favoriteEvent) }

    fun getAllFavorite(): LiveData<List<FavoriteEvent>> {
        _isLoading.postValue(true)
        return favoriteDao.getAllFavorite().apply {
            observeForever {
                _isLoading.postValue(false)
            }
        }
    }

    suspend fun getAllUpcomingEvent(): List<ListEventsItem>? {
        _isLoading.postValue(true)
        return try {
            delay(3000)
            val response = apiService.getEvents(active = 1)
            if (response.isSuccessful) {
                response.body()?.listEvents
            }else {
                null
            }
        } catch (e : Exception) {
            Log.d("error", e.message.toString())
            null
        } finally {
            _isLoading.postValue(false)
        }
    }

    suspend fun getAllFinishedEvent(): List<ListEventsItem>? {
        _isLoading.postValue(true)
        return try {
            val response = apiService.getEvents(active = 0)
            if (response.isSuccessful) {
                response.body()?.listEvents
            } else {
                null
            }
        } catch (e : Exception) {
            Log.d("error", e.message.toString())
            null
        } finally {
            _isLoading.postValue(false)
        }
    }

    suspend fun getDetailEvent(id: Int): Event? {
        _isLoading.postValue(true)
        return try {
            val response = apiService.getDetailEvent(id = id)
            if (response.isSuccessful){
                response.body()?.event
            } else {
                null
            }
        } catch (e: Exception){
            null
        } finally {
            _isLoading.postValue(false)
        }
    }

    companion object {
        @Volatile
        var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,
            favoriteDao: FavoriteDao,
            preferences: SettingPreferences
        ) : NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(
                    apiService = apiService,
                    favoriteDao = favoriteDao,
                    preferences = preferences
                )
            }.also { instance = it }
    }
}