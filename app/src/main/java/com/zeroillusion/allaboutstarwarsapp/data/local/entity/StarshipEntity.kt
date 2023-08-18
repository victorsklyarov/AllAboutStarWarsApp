package com.zeroillusion.allaboutstarwarsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zeroillusion.allaboutstarwarsapp.domain.model.Starship

@Entity
data class StarshipEntity(
    @PrimaryKey val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: String,
    val films: List<String>,
    val url: String,
    val favorite: Boolean
) {

    fun toStarship(): Starship {
        return Starship(
            name = name,
            model = model,
            manufacturer = manufacturer,
            passengers = passengers,
            films = films,
            url = url,
            favorite = favorite
        )
    }
}
