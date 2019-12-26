package com.ahmedroid.data.repositories

import com.ahmedroid.data.models.WeatherDisplayData
import io.reactivex.Observable

interface WeatherRepo {

    fun getWeatherInfoAt(city: String): Observable<WeatherDisplayData>
}