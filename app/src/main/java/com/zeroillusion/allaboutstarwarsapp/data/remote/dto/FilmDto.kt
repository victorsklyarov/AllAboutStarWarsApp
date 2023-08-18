package com.zeroillusion.allaboutstarwarsapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.FilmEntity

data class FilmDto(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    @SerializedName("episode_id")
    val episodeId: Int,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    val planets: List<String>,
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
) {

    fun toFilmEntity(): FilmEntity {
        return FilmEntity(
            title = title,
            director = director,
            producer = producer,
            url = url,
            favorite = false
        )
    }
}