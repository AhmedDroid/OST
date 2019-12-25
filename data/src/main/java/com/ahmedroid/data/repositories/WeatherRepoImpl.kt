package com.ahmedroid.data.repositories

import com.ahmedroid.data.BuildConfig
import com.ahmedroid.data.models.WeatherDisplayData
import com.ahmedroid.data.models.WeatherObject
import com.ahmedroid.data.service.WeatherAPIService
import io.reactivex.Single
import io.realm.Realm

class WeatherRepoImpl(
    private val weatherService: WeatherAPIService,
    private val realm: Realm
) : WeatherRepo {

    override fun getWeatherInfoAt(city: String): Single<WeatherDisplayData> {
        return getWeatherDisplayData(weatherService.getCarDetails(buildRequestQueries(city)).map { it.data })
    }

    private fun getWeatherDisplayData(weatherObject: Single<WeatherObject?>): Single<WeatherDisplayData> {
        return weatherObject.flatMap { weatherObject ->
            return@flatMap Single.just(WeatherDisplayData(
                cityName = weatherObject.request?.get(0)?.query,
                tempratureValue = weatherObject.currentCondition?.get(0)?.celesiusTemperature,
                weatherStatus = weatherObject.currentCondition?.get(0)?.weatherStatus?.get(0)?.value,
                weatherIconUrl = weatherObject.currentCondition?.get(0)?.weatherIconUrl?.get(0)?.value,
                weatherTempsHourly = weatherObject.weather?.get(0)?.hourly?.toTypedArray()
                )
            )
        }
    }

    private fun buildRequestQueries(city: String): Map<String, String?> {
        return mapOf(
            "q" to city,
            "key" to BuildConfig.API_KEY,
            "num_of_days" to "1",
            "tp" to "1",
            "format" to "json"
        )
    }

}