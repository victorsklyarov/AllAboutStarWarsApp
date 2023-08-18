package com.zeroillusion.allaboutstarwarsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zeroillusion.allaboutstarwarsapp.domain.model.Film

@Entity
data class FilmEntity(
    @PrimaryKey val title: String,
    val director: String,
    val producer: String,
    val url: String,
    val favorite: Boolean
) {

    fun toFilm(): Film {
        return Film(
            title = title,
            director = director,
            producer = producer,
            url = url,
            favorite = favorite
        )
    }
}
