package com.divar.data.repository

import com.divar.data.mapper.toDomain
import com.divar.data.utils.safeCall
import com.divar.domain.model.Category
import com.divar.domain.model.DataResult
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.repository.CategoryRepository
import com.divar.network.api.CategoryApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: CategoryApiService
) : CategoryRepository {
    override suspend fun getCategories(): Flow<DataResult<List<Category>>> = flow {
        safeCall { apiService.getCategories() }
            .onSuccess { data ->
                emit(DataResult.Success(data.map { it.toDomain() }))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

}