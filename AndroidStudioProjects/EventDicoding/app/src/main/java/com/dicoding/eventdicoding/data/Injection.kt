package com.dicoding.eventdicoding.data

import android.content.Context
import android.widget.Toast
import com.dicoding.eventdicoding.data.local.room.FavoriteDatabase
import com.dicoding.eventdicoding.data.retrofit.ApiConfig
import com.dicoding.eventdicoding.ui.ui.setting.SettingPreferences
import com.dicoding.eventdicoding.ui.ui.setting.dataStore

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val pref = SettingPreferences.getInstance(dataStore = context.dataStore)
        val db = FavoriteDatabase.getInstance(context = context)
        val dao = db.favoriteDao()
        return NewsRepository.getInstance(apiService, dao, pref)
    }

    fun messageError(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}