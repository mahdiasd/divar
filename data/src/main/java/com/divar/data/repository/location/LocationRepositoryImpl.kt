package com.divar.data.repository.location

import android.content.SharedPreferences
import com.divar.data.mapper.location.toDomain
import com.divar.data.utils.safeCall
import com.divar.domain.model.DataResult
import com.divar.domain.model.NotFoundError
import com.divar.domain.model.location.City
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.repository.location.LocationRepository
import com.divar.network.api.location.LocationApiService
import com.divar.secure_shared_pref.SharedPrefConstant
import com.divar.utils.fromJson
import com.divar.utils.json
import com.divar.utils.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiService: LocationApiService,
    private val sharedPreferences: SharedPreferences
) : LocationRepository {
    override suspend fun getCities(): Flow<DataResult<List<City>>> = flow {
        safeCall { apiService.getCities() }
            .onSuccess { data ->
                emit(DataResult.Success(data.map { it.toDomain() }))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

    override suspend fun saveCity(city: City) {
        city.toJson()?.let {
            sharedPreferences.edit().putString(SharedPrefConstant.USER_CITY, it).apply()
        }
    }

    override suspend fun getUserCity(): Flow<DataResult<City>> = flow {
        sharedPreferences.getString(SharedPrefConstant.USER_CITY, null)?.fromJson<City?>()?.let {
            emit(DataResult.Success(it))
        } ?: run {
            emit(DataResult.Failure(NotFoundError(404)))
        }
    }

}