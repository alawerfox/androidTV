package ru.kartyshova.androidtv.data

import retrofit2.http.GET

interface ChannelsApi {
    @GET("playlist/channels.json")
    suspend fun getChannels (): Response

    @GET
    suspend fun getPlayer (): ChannelInfo
}