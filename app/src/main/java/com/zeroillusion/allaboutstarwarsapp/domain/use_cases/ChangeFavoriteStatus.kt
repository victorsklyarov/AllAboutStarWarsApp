package com.zeroillusion.allaboutstarwarsapp.domain.use_cases

import com.zeroillusion.allaboutstarwarsapp.domain.model.Person
import com.zeroillusion.allaboutstarwarsapp.domain.model.Planet
import com.zeroillusion.allaboutstarwarsapp.domain.model.Starship
import com.zeroillusion.allaboutstarwarsapp.domain.repository.StarWarsRepository

class ChangeFavoriteStatus(
    private val repository: StarWarsRepository
) {

    suspend operator fun invoke(person: Person) {
        return repository.changeFavoriteStatus(person)
    }

    suspend operator fun invoke(starship: Starship) {
        return repository.changeFavoriteStatus(starship)
    }

    suspend operator fun invoke(planet: Planet) {
        return repository.changeFavoriteStatus(planet)
    }
}