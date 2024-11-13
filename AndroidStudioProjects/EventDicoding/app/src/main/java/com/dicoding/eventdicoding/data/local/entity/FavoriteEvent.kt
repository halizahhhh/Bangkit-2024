package com.dicoding.eventdicoding.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteEvent(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    var id: Int = 0,

    @ColumnInfo("name")
    var name: String = "",

    @ColumnInfo("summary")
    var summary: String = "",

    @ColumnInfo("media_cover")
    var mediaCover: String? = null

): Parcelable