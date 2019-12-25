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
    ) : RealmObject()