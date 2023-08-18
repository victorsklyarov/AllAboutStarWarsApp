package com.zeroillusion.allaboutstarwarsapp.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroillusion.allaboutstarwarsapp.domain.model.Person
import com.zeroillusion.allaboutstarwarsapp.domain.model.Planet
import com.zeroillusion.allaboutstarwarsapp.domain.model.Starship
import com.zeroillusion.allaboutstarwarsapp.domain.use_cases.ChangeFavoriteStatus
import com.zeroillusion.allaboutstarwarsapp.domain.use_cases.SearchByName
import com.zeroillusion.allaboutstarwarsapp.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val changeFavoriteStatus: ChangeFavoriteStatus,
    private val searchByName: SearchByName
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    private var changeFavoriteStatusJob: Job? = null

    private var searchByNameJob: Job? = null

    fun searchByName(search: String) {
        searchByNameJob?.cancel()
        searchByNameJob = viewModelScope.launch {
            delay(1000L)
            searchByName.invoke(search).onEach { result ->
                delay(500L)
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            people = result.data?.people ?: emptyList(),
                            starships = result.data?.starships ?: emptyList(),
                            planets = result.data?.planets ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(isLoading = false)
                        _eventFlow.emit(
                            UIEvent.ShowSnackbar(
                                result.message ?: "Unknown error"
                            )
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun resetState() { _state.value = MainState() }

    fun changeFavoriteStatus(person: Person) {
        changeFavoriteStatusJob?.cancel()
        changeFavoriteStatusJob = viewModelScope.launch {
            changeFavoriteStatus.invoke(person)
        }
    }

    fun changeFavoriteStatus(starship: Starship) {
        changeFavoriteStatusJob?.cancel()
        changeFavoriteStatusJob = viewModelScope.launch {
            changeFavoriteStatus.invoke(starship)
        }
    }

    fun changeFavoriteStatus(planet: Planet) {
        changeFavoriteStatusJob?.cancel()
        changeFavoriteStatusJob = viewModelScope.launch {
            changeFavoriteStatus.invoke(planet)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}