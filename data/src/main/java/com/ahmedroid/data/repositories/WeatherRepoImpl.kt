package com.ahmedroid.data.repositories

import android.util.Log
import com.ahmedroid.data.BuildConfig
import com.ahmedroid.data.models.UpdateEvent
import com.ahmedroid.data.service.WeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import org.greenrobot.eventbus.EventBus

class WeatherRepoImpl(
    private val weatherService: WeatherAPIService,
    private val realm: Realm
) : WeatherRepo {

    override fun getWeatherInfoAt(city: String) {
        weatherService.getCarDetails(buildRequestQueries(city))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weatherObject ->
                realm.executeTransaction { realm ->
                    realm.copyToRealm(weatherObject)
                    EventBus.getDefault().post(UpdateEvent())
                }
            }, {throwable ->
                Log.d("test", throwable.message)
            })
    }

    private fun buildRequestQueries(city: String): Map<String, String?> {
        return mapOf(
            "q" to city,
            "key" to BuildConfig.API_KEY,
            "num_of_days" to "3",
            "tp" to "1",
            "format" to "json"
        )
    }

}