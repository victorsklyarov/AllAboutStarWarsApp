package com.zeroillusion.allaboutstarwarsapp.presentation.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroillusion.allaboutstarwarsapp.domain.model.Person
import com.zeroillusion.allaboutstarwarsapp.domain.model.Planet
import com.zeroillusion.allaboutstarwarsapp.domain.model.Starship
import com.zeroillusion.allaboutstarwarsapp.domain.use_cases.ChangeFavoriteStatus
import com.zeroillusion.allaboutstarwarsapp.domain.use_cases.GetAllFavorites
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
class FavoriteViewModel @Inject constructor(
    private val changeFavoriteStatus: ChangeFavoriteStatus,
    private val getAllFavorites: GetAllFavorites
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _state = mutableStateOf(FavoriteState())
    val state: State<FavoriteState> = _state

    private var getAllFavoritesJob: Job? = null

    private var changeFavoriteStatusJob: Job? = null

    fun getAllFavorites() {
        _state.value = FavoriteState()
        getAllFavoritesJob?.cancel()
        getAllFavoritesJob = viewModelScope.launch {
            getAllFavorites.invoke().onEach { result ->
                delay(500L)
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            favoritePeople = result.data?.people ?: emptyList(),
                            favoriteStarships = result.data?.starships ?: emptyList(),
                            favoritePlanets = result.data?.planets ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            favoritePeople = result.data?.people ?: emptyList(),
                            favoriteStarships = result.data?.starships ?: emptyList(),
                            favoritePlanets = result.data?.planets ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(
                            UIEvent.ShowSnackbar(
                                result.message ?: "Unknown error"
                            )
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            favoritePeople = result.data?.people ?: emptyList(),
                            favoriteStarships = result.data?.starships ?: emptyList(),
                            favoritePlanets = result.data?.planets ?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

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