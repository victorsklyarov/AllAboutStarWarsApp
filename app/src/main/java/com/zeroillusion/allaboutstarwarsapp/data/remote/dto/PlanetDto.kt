package com.zeroillusion.allaboutstarwarsapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.PlanetEntity

data class PlanetDto(
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    @SerializedName("orbital_period")
    val orbitalPeriod: String,
    val population: String,
    val residents: List<String>,
    @SerializedName("rotation_period")
    val rotationPeriod: String,
    @SerializedName("surface_water")
    val surfaceWater: String,
    val terrain: String,
    val url: String
) {

    fun toPlanetEntity(): PlanetEntity {
        return PlanetEntity(
            name = name,
            diameter = diameter,
            population = population,
            url = url,
            favorite = false
        )
    }
}