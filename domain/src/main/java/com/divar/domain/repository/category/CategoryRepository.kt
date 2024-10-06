package com.divar.domain.repository.category

import com.divar.domain.model.DataResult
import com.divar.domain.model.category.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories(): Flow<DataResult<List<Category>>>

}