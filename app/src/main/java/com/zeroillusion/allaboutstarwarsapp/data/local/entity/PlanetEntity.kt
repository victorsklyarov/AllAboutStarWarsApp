package com.zeroillusion.allaboutstarwarsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zeroillusion.allaboutstarwarsapp.domain.model.Planet

@Entity
data class PlanetEntity(
    @PrimaryKey val name: String,
    val diameter: String,
    val population: String,
    val url: String,
    val favorite: Boolean
) {

    fun toPlanet(): Planet {
        return Planet(
            name = name,
            diameter = diameter,
            population = population,
            url = url,
            favorite = favorite
        )
    }
}
