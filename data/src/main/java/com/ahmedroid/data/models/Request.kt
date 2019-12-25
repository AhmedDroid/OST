package com.ahmedroid.data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Request(
    @SerializedName("type") val type: String,
    @SerializedName("query") val query: String
) : RealmObject() {
    constructor() : this(type = "", query = "")
}