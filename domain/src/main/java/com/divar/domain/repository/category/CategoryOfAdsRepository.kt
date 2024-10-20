package com.divar.domain.repository.category

import com.divar.domain.model.DataResult
import com.divar.domain.model.category.CategoryOfAds
import kotlinx.coroutines.flow.Flow

interface CategoryOfAdsRepository {

    suspend fun getCategoriesOfAds(searchText: String): Flow<DataResult<List<CategoryOfAds>>>

}