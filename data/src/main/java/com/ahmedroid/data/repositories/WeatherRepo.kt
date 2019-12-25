package com.ahmedroid.data.repositories

interface WeatherRepo {

    fun getWeatherInfoAt(city: String)
}