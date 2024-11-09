package com.divar.domain.repository.parameter

import com.divar.domain.model.DataResult
import com.divar.domain.model.location.City
import com.divar.domain.model.parameter.Parameter
import kotlinx.coroutines.flow.Flow

interface ParameterRepository {
    suspend fun getParameters(categoryId: Long): Flow<DataResult<List<Parameter>>>
}