package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Weather(
    @SerializedName("hourly") var hourly : RealmList<WeatherHourly>? = null
) : RealmObject()