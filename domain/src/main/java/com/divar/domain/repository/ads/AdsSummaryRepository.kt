package com.divar.domain.repository.ads

import com.divar.domain.model.DataResult
import com.divar.domain.model.ads.AdsSummary
import com.divar.domain.model.paginate.Paging
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface AdsSummaryRepository {

    suspend fun getAdsSummary(page: Int): Flow<DataResult<Paging<ImmutableList<AdsSummary>>>>

}