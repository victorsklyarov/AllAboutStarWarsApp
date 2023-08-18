package com.zeroillusion.allaboutstarwarsapp.presentation.favorite

import com.zeroillusion.allaboutstarwarsapp.domain.model.Person
import com.zeroillusion.allaboutstarwarsapp.domain.model.Planet
import com.zeroillusion.allaboutstarwarsapp.domain.model.Starship

data class FavoriteState(
    val favoritePeople: List<Person> = emptyList(),
    val favoriteStarships: List<Starship> = emptyList(),
    val favoritePlanets: List<Planet> = emptyList(),
    val isLoading: Boolean = false
)