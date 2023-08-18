package com.zeroillusion.allaboutstarwarsapp.domain.model

data class Starship(
    val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: String,
    val films: List<String>,
    val url: String,
    val favorite: Boolean
)
