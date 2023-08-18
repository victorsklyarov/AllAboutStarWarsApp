package com.zeroillusion.allaboutstarwarsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.FilmEntity
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.PersonEntity
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.PlanetEntity
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.StarshipEntity

@Dao
interface StarWarsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPeople(people: List<PersonEntity>)

    @Query("SELECT * FROM personentity WHERE name LIKE '%' || :search || '%'")
    suspend fun getAllPeopleBySearch(search: String): List<PersonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStarships(starships: List<StarshipEntity>)

    @Query("SELECT * FROM starshipentity WHERE name LIKE '%' || :search || '%'")
    suspend fun getAllStarshipsBySearch(search: String): List<StarshipEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlanets(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planetentity WHERE name LIKE '%' || :search || '%'")
    suspend fun getAllPlanetsBySearch(search: String): List<PlanetEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilms(films: List<FilmEntity>)

    //@Query("SELECT * FROM filmentity")
    //suspend fun getAllFilms(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changePersonFavoriteStatus(person: PersonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changeStarshipFavoriteStatus(starship: StarshipEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changePlanetFavoriteStatus(planet: PlanetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changeFilmFavoriteStatus(film: FilmEntity)

    @Query("SELECT * FROM personentity WHERE favorite")
    suspend fun getFavoritePeople(): List<PersonEntity>

    @Query("SELECT * FROM starshipentity WHERE favorite")
    suspend fun getFavoriteStarships(): List<StarshipEntity>

    @Query("SELECT * FROM planetentity WHERE favorite")
    suspend fun getFavoritePlanets(): List<PlanetEntity>

    @Query("SELECT * FROM filmentity WHERE favorite")
    suspend fun getFavoriteFilms(): List<FilmEntity>
}