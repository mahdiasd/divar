package com.divar.data.repository.ads

import com.divar.data.mapper.ads.toDomain
import com.divar.data.utils.safeCall
import com.divar.domain.model.DataResult
import com.divar.domain.model.ads.Ads
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.repository.ads.AdsRepository
import com.divar.network.api.ads.AdsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdsRepositoryImpl @Inject constructor(
    private val apiService: AdsApiService
) : AdsRepository {
    override suspend fun getAdsDetail(id: Long): Flow<DataResult<Ads>> = flow {
        safeCall {
            apiService.getAdsDetail(id)
        }.onSuccess { data ->
            emit(DataResult.Success(data.toDomain()))
        }.onFailure {
            emit(DataResult.Failure(it))
        }
    }


}