package ru.kartyshova.androidtv.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.kartyshova.androidtv.data.LocalRepository
import ru.kartyshova.androidtv.db.DomainChannel

class FavoriteViewModel(private val localRepository: LocalRepository) : ViewModel() {

    private val _status = MutableLiveData<List<DomainChannel>>()
    val status: LiveData<List<DomainChannel>> = _status

    init {
        localRepository.getFavorites()
            .onEach { _status.value = it }
            .launchIn(viewModelScope)
    }

    fun updateFavorite(domainChannel: DomainChannel) {
        viewModelScope.launch {
            if (domainChannel.favorite) {
                localRepository.deleteFavorites(domainChannel)
            } else {
                localRepository.addFavorites(domainChannel)
            }
        }
    }
}