package com.zeroillusion.allaboutstarwarsapp.data.remote.dto

data class FilmListDto(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<FilmDto>
)