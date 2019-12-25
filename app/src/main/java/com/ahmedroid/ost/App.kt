package com.ahmedroid.ost

import android.app.Application
import com.ahmedroid.data.BuildConfig
import com.ahmedroid.data.service.WeatherAPIService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    private val retrofit: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

    val weatherService: WeatherAPIService
        get() {
            return retrofit.create(WeatherAPIService::class.java)
        }

}