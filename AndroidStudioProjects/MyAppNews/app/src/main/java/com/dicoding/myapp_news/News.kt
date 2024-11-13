package com.dicoding.myapp_news

import android.media.audiofx.AudioEffect.Descriptor
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val descriptor: String,
    val photo: String,
    val detail: String,
    val from: String,
    val writer: String,
    val release: String
) : Parcelable
