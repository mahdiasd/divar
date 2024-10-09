package com.divar.domain.repository.location

import com.divar.domain.model.DataResult
import com.divar.domain.model.location.City
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getCities(): Flow<DataResult<List<City>>>

    suspend fun saveCity(city: City): Unit

    suspend fun getUserCity(): Flow<DataResult<City>>
}