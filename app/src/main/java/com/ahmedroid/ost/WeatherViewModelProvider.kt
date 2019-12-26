package com.ahmedroid.ost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmedroid.data.repositories.WeatherRepo

class WeatherViewModelProvider(
    private val app: App,
    private val weatherRepoImpl: WeatherRepo
) : ViewModelProvider.AndroidViewModelFactory(app) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherCardViewModel(app, weatherRepoImpl) as T
    }
}