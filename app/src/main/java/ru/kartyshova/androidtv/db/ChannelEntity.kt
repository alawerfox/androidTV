package ru.kartyshova.androidtv.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channel")
data class ChannelEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val imageUrl: String,
    val currentTvShow: String,
    val url: String
)