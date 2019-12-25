package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class WeatherObject(
    @SerializedName("request") val request : RealmList<Request>,
    @SerializedName("current_condition") val currentCondition : RealmList<CurrentCondition>
    ) : RealmObject() {
    constructor() : this(request = RealmList(), currentCondition = RealmList()) // to let realm create an empty obj
}