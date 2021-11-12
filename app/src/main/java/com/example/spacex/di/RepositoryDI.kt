package com.example.spacex.di

import com.example.spacex.data.repository.SpaceXRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { SpaceXRepository(get()) }
}