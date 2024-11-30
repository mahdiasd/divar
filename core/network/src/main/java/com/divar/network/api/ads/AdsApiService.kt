package com.divar.network.api.ads

import com.divar.network.dto.ads.AdsResponse
import com.divar.network.model.SuccessResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface AdsApiService {

    @GET("v1/ads/detail")
    suspend fun getAdsDetail(
        @Query("id") id: Long
    ): SuccessResponse<AdsResponse>

    @POST("v1/ads")
    @Multipart
    suspend fun createAds(
        @Part images: List<MultipartBody.Part>,
        @Part("ads") adsRequest: RequestBody
    ): SuccessResponse<AdsResponse>


}