package com.divar.network.api.category

import com.divar.network.dto.category.CategoryOfAdsResponse
import com.divar.network.dto.category.CategoryResponse
import com.divar.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryOfAdsApiService {

    @GET("v1/ads/categories_of_ads")
    suspend fun getCategoriesOfAds(
        @Query("searchText") searchText: String
    ): SuccessResponse<List<CategoryOfAdsResponse>>

}