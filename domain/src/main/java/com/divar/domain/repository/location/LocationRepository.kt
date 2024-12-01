package com.divar.domain.repository.location

import com.divar.domain.model.DataResult
import com.divar.domain.model.location.City
import com.divar.domain.model.location.Neighborhood
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getCities(): Flow<DataResult<List<City>>>
    suspend fun getCitiesWidthNeighborhoods(): Flow<DataResult<List<City>>>

    suspend fun saveCity(city: City): Unit
    suspend fun saveNeighborhood(neighborhood: Neighborhood): Unit

    suspend fun getUserCity(): Flow<DataResult<City>>

    suspend fun getUserNeighborhood(): Flow<DataResult<Neighborhood>>
}