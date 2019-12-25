package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class CurrentCondition(
    @SerializedName("temp_C") var celesiusTemperature: String? = null,
    @SerializedName("temp_F") var fahrenheitTemperature: String? = null,
    @SerializedName("weatherIconUrl") var weatherIconUrl: RealmList<WeatherIconUrl>? = null,
    @SerializedName("weatherDesc") var weatherStatus: RealmList<WeatherStatus>? = null
) : RealmObject()