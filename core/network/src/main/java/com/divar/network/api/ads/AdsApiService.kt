package com.divar.network.api.ads

import com.divar.network.dto.ads.AdsResponse
import com.divar.network.dto.paginate.PagingResponse
import com.divar.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AdsApiService {

    @GET("v1/ads/detail")
    suspend fun getAdsDetail(
        @Query("id") id: Long
    ): SuccessResponse<AdsResponse>

}