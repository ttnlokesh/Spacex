package com.example.spacex.di

import com.example.spacex.viewmodel.SpaceXViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SpaceXViewModel(get()) }
}