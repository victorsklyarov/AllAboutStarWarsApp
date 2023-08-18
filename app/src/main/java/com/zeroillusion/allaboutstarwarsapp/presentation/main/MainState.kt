package com.zeroillusion.allaboutstarwarsapp.presentation.main

import com.zeroillusion.allaboutstarwarsapp.domain.model.Film
import com.zeroillusion.allaboutstarwarsapp.domain.model.Person
import com.zeroillusion.allaboutstarwarsapp.domain.model.Planet
import com.zeroillusion.allaboutstarwarsapp.domain.model.Starship

data class MainState(
    val people: List<Person> = emptyList(),
    val starships: List<Starship> = emptyList(),
    val planets: List<Planet> = emptyList(),
    val films: List<Film> = emptyList(),
    val isLoading: Boolean = false
)