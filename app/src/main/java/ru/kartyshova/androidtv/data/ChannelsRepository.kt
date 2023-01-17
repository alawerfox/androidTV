package ru.kartyshova.androidtv.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import ru.kartyshova.androidtv.db.ChannelDao
import ru.kartyshova.androidtv.db.ChannelEntity
import ru.kartyshova.androidtv.db.DomainChannel

interface ChannelsRepository {
    suspend fun getAll(): List<DomainChannel>
}

class ChannelsRepositoryImpl(
    private val localRepository: LocalRepository,
    private val channelsApi: ChannelsApi
) : ChannelsRepository {

    override suspend fun getAll(): List<DomainChannel> {
        val response = channelsApi.getChannels()
        return response.channels.map {
            DomainChannel(
                it.name_ru,
                it.image,
                it.current.title,
                localRepository.isFavorite(it.name_ru),
                it.url
            )
        }
    }

}

class LocalRepository(private val channelDao: ChannelDao) {
    fun getFavorites(): Flow<List<DomainChannel>> {
        return channelDao.findAll()
            .map { entities ->
                entities.map { DomainChannel(it.title, it.imageUrl, it.currentTvShow, true, it.url) }
            }

    }

    suspend fun isFavorite(title: String): Boolean {
        return channelDao.isFavorite(title)
    }

    suspend fun addFavorites(domainChannel: DomainChannel) {
        channelDao.insert(
            ChannelEntity(
                title = domainChannel.name,
                imageUrl = domainChannel.imageUrl,
                currentTvShow = domainChannel.currentTvShow,
                url = domainChannel.url
            )
        )
    }

    suspend fun deleteFavorites(domainChannel: DomainChannel) {
        channelDao.delete(domainChannel.name)

    }
}