package com.zeroillusion.allaboutstarwarsapp.domain.use_cases

import com.zeroillusion.allaboutstarwarsapp.domain.model.Objects
import com.zeroillusion.allaboutstarwarsapp.domain.repository.StarWarsRepository
import com.zeroillusion.allaboutstarwarsapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class SearchByName(
    private val repository: StarWarsRepository
) {

    suspend operator fun invoke(search: String): Flow<Resource<Objects>> {
        return repository.searchByName(search)
    }
}