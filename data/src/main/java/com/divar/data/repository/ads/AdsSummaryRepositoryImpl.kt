package com.divar.data.repository.ads

import com.divar.data.mapper.ads.toDomain
import com.divar.data.mapper.paginate.toDomain
import com.divar.data.utils.safeCall
import com.divar.domain.model.DataResult
import com.divar.domain.model.ads.AdsSummary
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.model.paginate.Paging
import com.divar.domain.repository.ads.AdsSummaryRepository
import com.divar.network.api.ads.AdsSummaryApiService
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdsSummaryRepositoryImpl @Inject constructor(
    private val apiService: AdsSummaryApiService
) : AdsSummaryRepository {
    override suspend fun getAdsSummary(page: Int): Flow<DataResult<Paging<ImmutableList<AdsSummary>>>> = flow {
        safeCall { apiService.getAdsSummary(page = page) }
            .onSuccess { data ->
                val paging = data.toDomain(contentMapper = { it.map { adsSummaryResponse -> adsSummaryResponse.toDomain() }.toImmutableList() })
                emit(DataResult.Success(paging))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

}