package com.divar.data.repository.category

import com.divar.data.mapper.category.toDomain
import com.divar.data.utils.safeCall
import com.divar.domain.model.DataResult
import com.divar.domain.model.category.CategoryOfAds
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.repository.category.CategoryOfAdsRepository
import com.divar.network.api.category.CategoryOfAdsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryOfAdsRepositoryImpl @Inject constructor(
    private val apiService: CategoryOfAdsApiService
) : CategoryOfAdsRepository {

    override suspend fun getCategoriesOfAds(searchText: String): Flow<DataResult<List<CategoryOfAds>>> = flow{
        safeCall { apiService.getCategoriesOfAds(searchText) }
            .onSuccess { data ->
                emit(DataResult.Success(data.map { it.toDomain() }))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

}