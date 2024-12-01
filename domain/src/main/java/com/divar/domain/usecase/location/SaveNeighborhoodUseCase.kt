package com.divar.domain.usecase.location

import com.divar.domain.model.location.City
import com.divar.domain.model.location.Neighborhood
import com.divar.domain.repository.location.LocationRepository
import javax.inject.Inject

class SaveNeighborhoodUseCase @Inject constructor(
    private val repo: LocationRepository
) {
    suspend operator fun invoke(neighborhood: Neighborhood) {
        return repo.saveNeighborhood(neighborhood)
    }
}