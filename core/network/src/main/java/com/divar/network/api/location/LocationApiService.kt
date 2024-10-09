package com.divar.network.api.location

import com.divar.network.dto.category.CategoryResponse
import com.divar.network.dto.location.CityResponse
import com.divar.network.model.SuccessResponse
import retrofit2.http.GET

interface LocationApiService {

    @GET("v1/city")
    suspend fun getCities(): SuccessResponse<List<CityResponse>>

}