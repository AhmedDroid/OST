package com.ahmedroid.data.service

import com.ahmedroid.data.models.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherAPIService {

    @GET("weather.ashx")
    fun getWeatherDetails(
        @QueryMap(encoded = false) requestQuery: Map<String, String?>
    ): Single<WeatherResponse>
}