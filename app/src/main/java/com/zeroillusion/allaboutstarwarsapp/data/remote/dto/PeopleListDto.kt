package com.zeroillusion.allaboutstarwarsapp.data.remote.dto

data class PeopleListDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PersonDto>
)