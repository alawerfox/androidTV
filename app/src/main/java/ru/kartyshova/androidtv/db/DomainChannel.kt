package ru.kartyshova.androidtv.db

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainChannel(
    val name: String,
    val imageUrl: String,
    val currentTvShow: String,
    val favorite: Boolean,
    val url: String
):Parcelable