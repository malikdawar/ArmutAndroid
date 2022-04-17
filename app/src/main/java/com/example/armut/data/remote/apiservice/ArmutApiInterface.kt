package com.example.armut.data.remote.apiservice

import com.example.armut.data.model.HomeModel
import com.example.armut.data.model.Service
import com.example.armut.data.remote.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArmutApiInterface {

    @GET(GET_HOME_DETAILS)
    suspend fun fetchHomeUiItems(): ApiResponse<HomeModel>

    @GET("$GET_SERVICE_DETAILS{serviceId}")
    suspend fun fetchServiceById(@Path("serviceId") serviceId: String): ApiResponse<Service>

    companion object {
        const val GET_HOME_DETAILS = "home"
        const val GET_SERVICE_DETAILS = "service/"
    }
}