package com.divar.domain.repository

import com.divar.domain.model.Category
import com.divar.domain.model.DataResult
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories(): Flow<DataResult<List<Category>>>

}