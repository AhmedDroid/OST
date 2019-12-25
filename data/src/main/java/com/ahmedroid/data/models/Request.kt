package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Request(
    @SerializedName("type") var type: String,
    @SerializedName("query") var query: String
) : RealmObject() {
    constructor() : this(type = "", query = "")
}