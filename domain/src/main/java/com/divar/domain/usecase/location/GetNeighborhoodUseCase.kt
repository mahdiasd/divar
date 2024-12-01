package com.divar.domain.usecase.location

import com.divar.domain.model.DataResult
import com.divar.domain.model.location.City
import com.divar.domain.model.location.Neighborhood
import com.divar.domain.repository.location.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNeighborhoodUseCase @Inject constructor(
    private val repo: LocationRepository
) {
    suspend operator fun invoke(): Flow<DataResult<Neighborhood>> {
        return repo.getUserNeighborhood()
    }
}