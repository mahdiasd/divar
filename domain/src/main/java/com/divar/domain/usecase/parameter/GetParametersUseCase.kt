package com.divar.domain.usecase.parameter

import com.divar.domain.model.DataResult
import com.divar.domain.model.parameter.Parameter
import com.divar.domain.repository.parameter.ParameterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetParametersUseCase @Inject constructor(
    private val repo: ParameterRepository
) {
    suspend operator fun invoke(categoryId: Long): Flow<DataResult<List<Parameter>>> {
        return repo.getParameters(categoryId)
    }
}