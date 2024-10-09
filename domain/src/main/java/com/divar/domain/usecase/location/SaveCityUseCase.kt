package com.divar.domain.usecase.location

import com.divar.domain.model.location.City
import com.divar.domain.repository.location.LocationRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val repo: LocationRepository
) {
    suspend operator fun invoke(city: City) {
        return repo.saveCity(city)
    }
}