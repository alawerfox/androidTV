package ru.kartyshova.androidtv.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Response(
    val channels: List<ChannelInfo>
) : Parcelable

@Parcelize
data class ChannelInfo(
    val name_ru: String,
    val image: String,
    val current: Current,
    val url: String,
) : Parcelable

@Parcelize
data class Current(
    val title: String
) : Parcelable
