package com.zeroillusion.allaboutstarwarsapp.domain.use_cases

import com.zeroillusion.allaboutstarwarsapp.domain.model.Objects
import com.zeroillusion.allaboutstarwarsapp.domain.repository.StarWarsRepository
import com.zeroillusion.allaboutstarwarsapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetAllFavorites(
    private val repository: StarWarsRepository
) {

    suspend operator fun invoke(): Flow<Resource<Objects>> {
        return repository.getAllFavorites()
    }
}