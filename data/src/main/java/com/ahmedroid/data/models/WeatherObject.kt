package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class WeatherObject(
    @SerializedName("request") var request : RealmList<Request>,
    @SerializedName("current_condition") var currentCondition : RealmList<CurrentCondition>
    ) : RealmObject() {
    constructor() : this(request = RealmList(), currentCondition = RealmList()) // to let realm create an empty obj
}