package com.divar.domain.usecase.ads

import com.divar.domain.model.ads.CreateAdsParam
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.repository.ads.AdsRepository
import com.divar.domain.repository.ads.AdsSummaryRepository
import javax.inject.Inject

class CreateAdsUseCase @Inject constructor(
    private val repo: AdsRepository
) {
    suspend operator fun invoke(
        createAdsParam: CreateAdsParam
    ) = repo.createAds(
        createAdsParam = createAdsParam
    )
}