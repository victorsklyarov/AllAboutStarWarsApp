package com.zeroillusion.allaboutstarwarsapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.StarshipEntity

data class StarshipDto(
    @SerializedName("cargo_capacity")
    val cargoCapacity: String,
    val consumables: String,
    @SerializedName("cost_in_credits")
    val costInCredits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    @SerializedName("hyperdrive_rating")
    val hyperdriveRating: String,
    val length: String,
    @SerializedName("MGLT")
    val mGLT: String,
    val manufacturer: String,
    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,
    val model: String,
    val name: String,
    val passengers: String,
    val pilots: List<String>,
    @SerializedName("starship_class")
    val starshipClass: String,
    val url: String
) {

    fun toStarshipEntity(): StarshipEntity {
        return StarshipEntity(
            name = name,
            model = model,
            manufacturer = manufacturer,
            passengers = passengers,
            films = films,
            url = url,
            favorite = false
        )
    }
}