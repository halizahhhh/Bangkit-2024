package com.dicoding.eventdicoding.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.eventdicoding.data.local.entity.FavoriteEvent

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorite(favoriteEvent: FavoriteEvent)

    @Delete
    fun deleteFavorite(favoriteEvent: FavoriteEvent)

    @Query("SELECT * FROM favoriteevent")
    fun getAllFavorite(): LiveData<List<FavoriteEvent>>

    @Query("SELECT * FROM favoriteevent WHERE id = :id")
    fun getFavoriteById(id: Int): LiveData<FavoriteEvent>

}