package com.zeroillusion.allaboutstarwarsapp.data.remote

import com.zeroillusion.allaboutstarwarsapp.data.remote.dto.FilmListDto
import com.zeroillusion.allaboutstarwarsapp.data.remote.dto.PeopleListDto
import com.zeroillusion.allaboutstarwarsapp.data.remote.dto.PlanetListDto
import com.zeroillusion.allaboutstarwarsapp.data.remote.dto.StarshipListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SWAPI {

    @GET("/api/people/")
    suspend fun getAllPeopleBySearch(
        @Query("search") search: String,
        @Query("format") format: String = "json"
    ) : PeopleListDto

    @GET("/api/starships/")
    suspend fun getAllStarshipsBySearch(
        @Query("search") search: String,
        @Query("format") format: String = "json"
    ) : StarshipListDto

    @GET("/api/planets/")
    suspend fun getAllPlanetsBySearch(
        @Query("search") search: String,
        @Query("format") format: String = "json"
    ) : PlanetListDto

    @GET("/api/films/")
    suspend fun getAllFilms(
        @Query("format") format: String = "json",
    ) : FilmListDto
}