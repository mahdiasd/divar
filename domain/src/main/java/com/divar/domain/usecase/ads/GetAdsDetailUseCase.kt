package com.divar.domain.usecase.ads

import com.divar.domain.repository.ads.AdsRepository
import javax.inject.Inject

class GetAdsDetailUseCase @Inject constructor(
    private val repo: AdsRepository
) {
    suspend operator fun invoke(
        id: Long
    ) = repo.getAdsDetail(
        id = id
    )
}