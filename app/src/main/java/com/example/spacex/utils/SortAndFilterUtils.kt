package com.example.spacex.utils

import com.example.spacex.data.network.response.LaunchResponseItem

fun sortByMissionName(launchList: ArrayList<LaunchResponseItem>): ArrayList<LaunchResponseItem> {
    launchList.sortBy { it.missionName }
    return launchList
}

fun filterByLaunchSuccess(launchList: ArrayList<LaunchResponseItem>): ArrayList<LaunchResponseItem> {
    val list = launchList.filter { it.launchSuccess == true }
    return (list as ArrayList<LaunchResponseItem>)
}

fun filterByLaunchUnSuccess(launchList: ArrayList<LaunchResponseItem>): ArrayList<LaunchResponseItem> {
    val list = launchList.filter { it.launchSuccess == false }
    return (list as ArrayList<LaunchResponseItem>)
}