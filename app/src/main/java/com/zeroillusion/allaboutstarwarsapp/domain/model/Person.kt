package com.zeroillusion.allaboutstarwarsapp.domain.model

data class Person(
    val name: String,
    val gender: String,
    val starshipsCount: Int,
    val films: List<String>,
    val url: String,
    val favorite: Boolean
)
