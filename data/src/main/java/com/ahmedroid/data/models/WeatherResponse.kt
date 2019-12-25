package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class WeatherResponse(
    @SerializedName("data") var data: WeatherObject? = null
) : RealmObject()