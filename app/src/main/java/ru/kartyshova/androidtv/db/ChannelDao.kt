package ru.kartyshova.androidtv.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelDao {

    @Query("select * from channel")
    fun findAll(): Flow<List<ChannelEntity>>

    @Query("SELECT EXISTS(SELECT * FROM channel WHERE title = :title)")
    suspend fun isFavorite(title: String): Boolean

    @Insert
    suspend fun insert(channel: ChannelEntity)

    @Query("DELETE FROM channel WHERE title = :title")
    suspend fun delete(title: String)
}