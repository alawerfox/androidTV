package ru.kartyshova.androidtv.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kartyshova.androidtv.data.LocalRepository
import ru.kartyshova.androidtv.data.Response
import ru.kartyshova.androidtv.db.DomainChannel
import ru.kartyshova.androidtv.usecase.GetChannelsUseCase

class ChannelsViewModel(
    private val getChannelsUseCase: GetChannelsUseCase,
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _status = MutableLiveData<List<DomainChannel>>()
    val status: LiveData<List<DomainChannel>> = _status

    init {
        getAll()
    }

    fun getAll() {
        viewModelScope.launch {
            try {
                val listResult = getChannelsUseCase()
                _status.value = listResult
            } catch (e: Exception) {
                Log.e("MyTag", "Error", e)
            }
        }
    }

    fun addFavorites(domainChannel: DomainChannel) {
        viewModelScope.launch {
            if (domainChannel.favorite) {
                localRepository.deleteFavorites(domainChannel)
            } else {
                localRepository.addFavorites(domainChannel)
            }
        }

        getAll()
    }
}
