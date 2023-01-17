package ru.kartyshova.androidtv.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kartyshova.androidtv.data.ChannelsRepository
import ru.kartyshova.androidtv.data.Response
import ru.kartyshova.androidtv.db.DomainChannel

class GetChannelsUseCase(private val channelsRepository: ChannelsRepository) {
    suspend operator fun invoke(): List<DomainChannel> {
        return channelsRepository.getAll()
    }

}