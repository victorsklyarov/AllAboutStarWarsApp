package com.zeroillusion.allaboutstarwarsapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.PersonEntity

data class PersonDto(
    @SerializedName("birth_year")
    val birthYear: String,
    val created: String,
    val edited: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    val films: List<String>,
    val gender: String,
    @SerializedName("hair_color")
    val hairColor: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    @SerializedName("skin_color")
    val skinColor: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
) {

    fun toPersonEntity(): PersonEntity {
        return PersonEntity(
            name = name,
            gender = gender,
            starshipsCount = starships.size,
            films = films,
            url = url,
            favorite = false
        )
    }
}