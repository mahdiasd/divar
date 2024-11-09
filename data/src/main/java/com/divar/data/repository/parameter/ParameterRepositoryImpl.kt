package com.divar.data.repository.parameter

import android.content.SharedPreferences
import com.divar.data.mapper.parameter.toDomain
import com.divar.data.utils.safeCall
import com.divar.domain.model.DataResult
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.model.parameter.Parameter
import com.divar.domain.repository.parameter.ParameterRepository
import com.divar.network.api.parameter.ParameterApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ParameterRepositoryImpl @Inject constructor(
    private val apiService: ParameterApiService,
    private val sharedPreferences: SharedPreferences
) : ParameterRepository {
    override suspend fun getParameters(categoryId: Long): Flow<DataResult<List<Parameter>>> = flow {
        safeCall { apiService.getParameters(categoryId) }
            .onSuccess { data ->
                emit(DataResult.Success(data.map { it.toDomain() }))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }
}