package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class WeatherHourly(
    @SerializedName("time") var time: String? = null,
    @SerializedName("tempC") var tempC: String? = null,
    @SerializedName("tempF") var tempF: String? = null,
    @SerializedName("weatherIconUrl") var weatherIconUrl: RealmList<WeatherIconUrl>? = null
) : RealmObject()