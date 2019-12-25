package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class WeatherStatus(
    @SerializedName("value") var value: String? = null
) : RealmObject()