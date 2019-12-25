package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class WeatherIconUrl(
    @SerializedName("value") val value: String
) : RealmObject() {
    constructor() : this(value = "")
}