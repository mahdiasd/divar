package com.divar.domain.usecase.ads

import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.repository.ads.AdsSummaryRepository
import javax.inject.Inject

class GetAdsSummaryUseCase @Inject constructor(
    private val repo: AdsSummaryRepository
) {
    suspend operator fun invoke(
        adsFilter: AdsFilter,
        page: Int,
        cityId: Long,
    ) = repo.getAdsSummary(
        page = page,
        cityId = cityId,
        adsFilter = adsFilter
    )
}