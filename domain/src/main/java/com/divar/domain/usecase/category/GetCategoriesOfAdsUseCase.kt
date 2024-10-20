package com.divar.domain.usecase.category

import com.divar.domain.model.DataResult
import com.divar.domain.model.category.CategoryOfAds
import com.divar.domain.repository.category.CategoryOfAdsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesOfAdsUseCase @Inject constructor(
    private val repo: CategoryOfAdsRepository
) {
    suspend operator fun invoke(searchText: String): Flow<DataResult<List<CategoryOfAds>>> {
        return repo.getCategoriesOfAds(searchText)
    }
}