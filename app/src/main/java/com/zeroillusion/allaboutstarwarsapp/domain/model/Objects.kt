package com.zeroillusion.allaboutstarwarsapp.domain.model

data class Objects(
    val people: List<Person>? = null,
    val starships: List<Starship>? = null,
    val planets: List<Planet>? = null,
    val films: List<Film>? = null
)
