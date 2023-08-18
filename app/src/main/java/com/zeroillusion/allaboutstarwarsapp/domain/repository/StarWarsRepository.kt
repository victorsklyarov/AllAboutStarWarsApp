package com.zeroillusion.allaboutstarwarsapp.domain.repository

import com.zeroillusion.allaboutstarwarsapp.domain.model.Objects
import com.zeroillusion.allaboutstarwarsapp.domain.model.Person
import com.zeroillusion.allaboutstarwarsapp.domain.model.Planet
import com.zeroillusion.allaboutstarwarsapp.domain.model.Starship
import com.zeroillusion.allaboutstarwarsapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository {

    suspend fun searchByName(search: String): Flow<Resource<Objects>>

    suspend fun changeFavoriteStatus(person: Person)

    suspend fun changeFavoriteStatus(starship: Starship)

    suspend fun changeFavoriteStatus(planet: Planet)

    suspend fun getAllFavorites(): Flow<Resource<Objects>>
}