package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class CurrentCondition(
    @SerializedName("temp_C") val celesiusTemperature: Int,
    @SerializedName("temp_F") val fahrenheitTemperature: Int,
    @SerializedName("observation_time") val observationTime: String,
    @SerializedName("weatherIconUrl") val weatherIconUrl: RealmList<WeatherIconUrl>
) : RealmObject() {
    constructor() : this(
        celesiusTemperature = -1,
        fahrenheitTemperature = -1,
        observationTime = "",
        weatherIconUrl = RealmList()
    )
}