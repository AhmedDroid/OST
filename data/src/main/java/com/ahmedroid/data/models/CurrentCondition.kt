package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class CurrentCondition(
    @SerializedName("temp_C") var celesiusTemperature: Int,
    @SerializedName("temp_F") var fahrenheitTemperature: Int,
    @SerializedName("observation_time") var observationTime: String,
    @SerializedName("weatherIconUrl") var weatherIconUrl: RealmList<WeatherIconUrl>
) : RealmObject() {
    constructor() : this(
        celesiusTemperature = -1,
        fahrenheitTemperature = -1,
        observationTime = "",
        weatherIconUrl = RealmList()
    )
}