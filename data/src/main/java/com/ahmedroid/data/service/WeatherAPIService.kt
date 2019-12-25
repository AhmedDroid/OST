package com.ahmedroid.data.service

import com.ahmedroid.data.models.WeatherObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherAPIService {

    @GET("weather.ashx")
    fun getCarDetails(
        @QueryMap(encoded = false) requestQuery: Map<String, String?>
    ): Single<WeatherObject>
}