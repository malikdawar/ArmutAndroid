package com.example.armut.data.remote.apiservice

import com.example.armut.data.model.HomeModel
import com.example.armut.data.remote.ApiResponse
import retrofit2.http.GET

interface ArmutApiInterface {

    @GET(GET_MOVIES)
    suspend fun fetchHomeUiItems(): ApiResponse<HomeModel>

    companion object {
        const val GET_MOVIES = "v2/5dea8d843000001d442b09da"
    }
}