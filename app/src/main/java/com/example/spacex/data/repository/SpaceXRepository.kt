package com.example.spacex.data.repository

import com.example.spacex.data.network.api.ApiInterface

class SpaceXRepository(private val apiService: ApiInterface) {
    suspend fun getLaunchList() = apiService.getLaunchList()

    suspend fun getRocketInfo(rocketId: String) = apiService.getRocketInfo(rocketId)
}