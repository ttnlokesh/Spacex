package com.example.spacex.data.network.api

import com.example.spacex.data.network.response.LaunchResponseItem
import com.example.spacex.data.network.response.RocketResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET(LAUNCHES_API)
    suspend fun getLaunchList(): Response<ArrayList<LaunchResponseItem>>

    @GET(ROCKETS_API)
    suspend fun getRocketInfo(@Path("rocket_id") id: String): Response<RocketResponse>
}