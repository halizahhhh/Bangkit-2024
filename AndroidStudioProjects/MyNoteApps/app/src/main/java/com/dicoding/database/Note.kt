package com.dicoding.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Note (
    //@PrimaryKey digunakan untuk menjadikan sebuah column menjadi primary key
    @PrimaryKey(autoGenerate = true)
    //@ColumnInfo digunakan untuk memberi nama column dari tabel
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null
) : Parcelable