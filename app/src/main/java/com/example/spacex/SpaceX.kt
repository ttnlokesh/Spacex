package com.example.spacex

import android.app.Application
import com.example.spacex.di.apiModule
import com.example.spacex.di.repositoryModule
import com.example.spacex.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SpaceX: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SpaceX)
            modules(apiModule, repositoryModule, viewModelModule)
        }
    }
}