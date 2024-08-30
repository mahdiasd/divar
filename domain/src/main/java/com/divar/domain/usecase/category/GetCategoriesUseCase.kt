package com.divar.domain.usecase.category

import com.divar.domain.model.DataResult
import com.divar.domain.model.category.Category
import com.divar.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repo: CategoryRepository
) {
    suspend operator fun invoke(): Flow<DataResult<List<Category>>> {
        return repo.getCategories()
    }
}