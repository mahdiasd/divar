package com.divar.network.api.location

import com.divar.network.dto.location.CityResponse
import com.divar.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET("v1/city")
    suspend fun getCities(): SuccessResponse<List<CityResponse>>

    @GET("v1/city")
    suspend fun getCitiesWithNeighborhood(
        @Query("includeNeighborhoods") includeNeighborhoods: Boolean = true
    ): SuccessResponse<List<CityResponse>>

}