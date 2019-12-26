package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class WeatherObject(
    @SerializedName("request") var request : RealmList<Request>? = null,
    @SerializedName("current_condition") var currentCondition : RealmList<CurrentCondition>? = null,
    @SerializedName("weather") var weather: RealmList<Weather>? = null
    ) : RealmObject() {

    fun toWeatherDisplayData() : WeatherDisplayData {
        return WeatherDisplayData(
            cityName = this.request?.get(0)?.query,
            tempratureValue = this.currentCondition?.get(0)?.celesiusTemperature,
            weatherStatus = this.currentCondition?.get(0)?.weatherStatus?.get(0)?.value,
            weatherIconUrl = this.currentCondition?.get(0)?.weatherIconUrl?.get(0)?.value,
            weatherTempsHourly = this.weather?.get(0)?.hourly?.toTypedArray()
        )
    }
}