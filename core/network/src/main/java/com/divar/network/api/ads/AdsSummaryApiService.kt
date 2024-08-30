package com.divar.network.api.ads

import com.divar.network.dto.ads.AdsSummaryResponse
import com.divar.network.dto.paginate.PagingResponse
import com.divar.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AdsSummaryApiService {

    @GET("v1/ads")
    suspend fun getAdsSummary(
        @Query("page") page: Int = 0
    ): SuccessResponse<PagingResponse<List<AdsSummaryResponse>>>

}