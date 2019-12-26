package com.ahmedroid.data.repositories

import com.ahmedroid.data.BuildConfig
import com.ahmedroid.data.models.WeatherDisplayData
import com.ahmedroid.data.models.WeatherObject
import com.ahmedroid.data.service.WeatherAPIService
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm

class WeatherRepoImpl(
    private val weatherService: WeatherAPIService
) : WeatherRepo {

    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun getWeatherInfoAt(city: String): Observable<WeatherDisplayData> {

        val localWeatherDisplayData = if (realm.where(WeatherObject::class.java).findAll().size > 0) {
            realm.where(WeatherObject::class.java).findAll().last()
                ?.toWeatherDisplayData()
        } else {
            null
        }
        Realm.getDefaultInstance()

        return Observable.create<WeatherDisplayData> {
            if (localWeatherDisplayData != null) {
                it.onNext(localWeatherDisplayData)
            }

            it.onNext(
                getWeatherDisplayData(
                    weatherService.getWeatherDetails(
                        buildRequestQueries(
                            city
                        )
                    ).map { it.data }).blockingGet()
            )
        }
    }

    private fun getWeatherDisplayData(weatherObject: Single<WeatherObject?>): Single<WeatherDisplayData> {
        return weatherObject.flatMap { weatherObject ->
            Realm.getDefaultInstance().executeTransaction {
                it.copyToRealm(weatherObject)
            }
            return@flatMap Single.just(weatherObject.toWeatherDisplayData())
        }
    }

    private fun buildRequestQueries(city: String): Map<String, String?> {
        return mapOf(
            "q" to city,
            "key" to BuildConfig.API_KEY,
            "num_of_days" to "1",
            "tp" to "1",
            "format" to "json"
        )
    }
}