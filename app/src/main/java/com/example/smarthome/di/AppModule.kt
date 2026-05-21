package com.example.smarthome.di

import com.example.smarthome.data.MockSmartHomeRepository
import com.example.smarthome.presentation.HomeScreenViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val appModule = module {
    single {
        MockSmartHomeRepository()
    }
    viewModel { HomeScreenViewModel(get()) }
}