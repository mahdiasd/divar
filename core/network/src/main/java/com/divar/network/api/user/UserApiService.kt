package com.divar.network.api.user

import com.divar.network.dto.user.UserRequest
import com.divar.network.dto.user.UserResponse
import com.divar.network.model.SuccessResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApiService {

    @POST("v1/user/login")
    suspend fun login(
        @Body userRequest: UserRequest
    ): SuccessResponse<UserResponse>

    @POST("v1/user/register")
    suspend fun register(
       @Body userRequest: UserRequest
    ): SuccessResponse<UserResponse>

}