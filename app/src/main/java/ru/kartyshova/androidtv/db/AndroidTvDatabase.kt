package ru.kartyshova.androidtv.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ChannelEntity::class ], version = 1)
abstract class AndroidTvDatabase : RoomDatabase() {

    abstract fun channelDao(): ChannelDao
}