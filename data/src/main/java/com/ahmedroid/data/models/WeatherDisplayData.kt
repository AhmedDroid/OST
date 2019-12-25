package com.ahmedroid.data.models

data class WeatherDisplayData(
    var cityName: String? = null,
    var weatherStatus: String? = null,
    var tempratureValue: String? = null,
    var weatherIconUrl: String?,
    var weatherTempsHourly: Array<WeatherHourly>? = null
)