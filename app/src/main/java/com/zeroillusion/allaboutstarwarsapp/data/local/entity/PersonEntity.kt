package com.zeroillusion.allaboutstarwarsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zeroillusion.allaboutstarwarsapp.domain.model.Person

@Entity
data class PersonEntity(
    @PrimaryKey val name: String,
    val gender: String,
    val starshipsCount: Int,
    val films: List<String>,
    val url: String,
    val favorite: Boolean
) {

    fun toPerson(): Person {
        return Person(
            name = name,
            gender = gender,
            starshipsCount = starshipsCount,
            films = films,
            url = url,
            favorite = favorite
        )
    }
}