package com.zeroillusion.allaboutstarwarsapp.data.repository

import com.zeroillusion.allaboutstarwarsapp.data.local.StarWarsDao
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.PersonEntity
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.PlanetEntity
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.StarshipEntity
import com.zeroillusion.allaboutstarwarsapp.data.remote.SWAPI
import com.zeroillusion.allaboutstarwarsapp.domain.model.Objects
import com.zeroillusion.allaboutstarwarsapp.domain.model.Person
import com.zeroillusion.allaboutstarwarsapp.domain.model.Planet
import com.zeroillusion.allaboutstarwarsapp.domain.model.Starship
import com.zeroillusion.allaboutstarwarsapp.domain.repository.StarWarsRepository
import com.zeroillusion.allaboutstarwarsapp.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class StarWarsRepositoryImpl(
    private val api: SWAPI,
    private val dao: StarWarsDao
) : StarWarsRepository {

    override suspend fun searchByName(search: String): Flow<Resource<Objects>> = flow {
        emit(Resource.Loading())

        val objectList = Objects(
            people = dao.getAllPeopleBySearch(search).map { it.toPerson() },
            starships = dao.getAllStarshipsBySearch(search).map { it.toStarship() },
            planets = dao.getAllPlanetsBySearch(search).map { it.toPlanet() }
        )

        emit(Resource.Loading(data = objectList))

        try {
            coroutineScope {
                dao.insertPeople(
                    async(Dispatchers.IO) {
                        api.getAllPeopleBySearch(search)
                    }.await().results.map { it.toPersonEntity() }
                )
                dao.insertStarships(
                    async(Dispatchers.IO) {
                        api.getAllStarshipsBySearch(search)
                    }.await().results.map { it.toStarshipEntity() })

                dao.insertPlanets(async(Dispatchers.IO) {
                    api.getAllPlanetsBySearch(search)
                }.await().results.map { it.toPlanetEntity() })
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "HttpException",
                    data = objectList
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "No internet connection",
                    data = objectList
                )
            )
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.message ?: "Unknown error",
                    data = objectList
                )
            )
        }

        val newObjectList = Objects(
            people = dao.getAllPeopleBySearch(search).map { it.toPerson() },
            starships = dao.getAllStarshipsBySearch(search).map { it.toStarship() },
            planets = dao.getAllPlanetsBySearch(search).map { it.toPlanet() }
        )
        emit(Resource.Success(newObjectList))
    }

    override suspend fun changeFavoriteStatus(person: Person) {
        dao.changePersonFavoriteStatus(
            PersonEntity(
                name = person.name,
                gender = person.gender,
                starshipsCount = person.starshipsCount,
                films = person.films,
                url = person.url,
                favorite = !person.favorite
            )
        )
    }

    override suspend fun changeFavoriteStatus(starship: Starship) {
        dao.changeStarshipFavoriteStatus(
            StarshipEntity(
                name = starship.name,
                model = starship.model,
                manufacturer = starship.manufacturer,
                passengers = starship.passengers,
                films = starship.films,
                url = starship.url,
                favorite = !starship.favorite
            )
        )
    }

    override suspend fun changeFavoriteStatus(planet: Planet) {
        dao.changePlanetFavoriteStatus(
            PlanetEntity(
                name = planet.name,
                diameter = planet.diameter,
                population = planet.population,
                url = planet.url,
                favorite = !planet.favorite
            )
        )
    }

    override suspend fun getAllFavorites(): Flow<Resource<Objects>> = flow {
        try {
            emit(Resource.Loading())

            val favoriteObjects = Objects(
                dao.getFavoritePeople().map { it.toPerson() },
                dao.getFavoriteStarships().map { it.toStarship() },
                dao.getFavoritePlanets().map { it.toPlanet() },
                dao.getFavoriteFilms().map { it.toFilm() }
            )

            emit(Resource.Success(data = favoriteObjects))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.message ?: "Unknown error",
                    data = null
                )
            )
        }
    }
}