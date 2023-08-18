package com.zeroillusion.allaboutstarwarsapp.data.remote.dto

data class StarshipListDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<StarshipDto>
)