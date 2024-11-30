package com.divar.domain.repository.ads

import com.divar.domain.model.DataResult
import com.divar.domain.model.ads.Ads
import com.divar.domain.model.ads.AdsSummary
import com.divar.domain.model.ads.CreateAdsParam
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.model.paginate.Paging
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface AdsRepository {

    suspend fun getAdsDetail(id: Long): Flow<DataResult<Ads>>

    suspend fun createAds(createAdsParam: CreateAdsParam): Flow<DataResult<Unit>>
}