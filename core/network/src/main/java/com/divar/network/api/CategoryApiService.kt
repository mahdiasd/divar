package com.divar.network.api

import com.divar.network.dto.CategoryResponse
import com.divar.network.model.SuccessResponse
import retrofit2.http.GET

interface CategoryApiService {

    @GET("v1/category")
    suspend fun getCategories(): SuccessResponse<List<CategoryResponse>>

}