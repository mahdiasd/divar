package com.divar.network.api.ads

import com.divar.network.dto.ads.AdsSummaryResponse
import com.divar.network.dto.ads.GetAdsRequest
import com.divar.network.dto.paginate.PagingResponse
import com.divar.network.model.SuccessResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AdsSummaryApiService {

    @POST("v1/ads/filter")
    suspend fun getAdsSummary(
        @Body getAdsRequest: GetAdsRequest
    ): SuccessResponse<PagingResponse<List<AdsSummaryResponse>>>

}