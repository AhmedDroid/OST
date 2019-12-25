package com.ahmedroid.data.repositories

import com.ahmedroid.data.models.WeatherDisplayData
import io.reactivex.Single

interface WeatherRepo {

    fun getWeatherInfoAt(city: String): Single<WeatherDisplayData>
}