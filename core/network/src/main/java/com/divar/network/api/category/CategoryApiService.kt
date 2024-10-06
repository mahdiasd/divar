package com.divar.network.api.category

import com.divar.network.dto.category.CategoryResponse
import com.divar.network.model.SuccessResponse
import retrofit2.http.GET

interface CategoryApiService {

    @GET("v1/category")
    suspend fun getCategories(): SuccessResponse<List<CategoryResponse>>

}