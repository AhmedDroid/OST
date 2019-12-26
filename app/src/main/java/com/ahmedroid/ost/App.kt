package com.ahmedroid.ost

import android.app.Application
import com.ahmedroid.data.BuildConfig
import com.ahmedroid.data.service.WeatherAPIService
import com.google.android.libraries.places.api.Places
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.realm.Realm
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    private val retrofit: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient())
                .build()
        }

    val weatherService: WeatherAPIService
        get() {
            return retrofit.create(WeatherAPIService::class.java)
        }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        if (!Places.isInitialized()) {
            Places.initialize(this, BuildConfig.GOOGLE_PLACES_API_KEY)
        }
    }
}