package com.example.spacex.di

import com.example.spacex.data.network.api.ApiController
import com.example.spacex.data.network.api.ApiInterface
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val apiModule = module {
    single {
        ApiController.createWebService<ApiInterface>(
            androidContext(),
            "https://api.spacexdata.com/v3/"
        )
    }
}