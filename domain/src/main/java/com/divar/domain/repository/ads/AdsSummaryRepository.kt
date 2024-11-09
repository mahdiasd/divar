package com.divar.domain.repository.ads

import com.divar.domain.model.DataResult
import com.divar.domain.model.ads.AdsSummary
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.model.paginate.Paging
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface AdsSummaryRepository {

    suspend fun getAdsSummary(
        adsFilter: AdsFilter,
        page: Int,
        cityId: Long,
    ): Flow<DataResult<Paging<ImmutableList<AdsSummary>>>>

}