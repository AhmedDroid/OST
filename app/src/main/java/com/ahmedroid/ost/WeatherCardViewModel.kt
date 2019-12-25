package com.ahmedroid.ost

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.ahmedroid.data.models.WeatherObject
import com.ahmedroid.data.repositories.WeatherRepo
import io.reactivex.disposables.CompositeDisposable


class WeatherCardViewModel(private val weatherRepo: WeatherRepo) : AndroidViewModel(Application()) {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getWeatherAt(city: String): LiveData<WeatherObject> {
        val liveData = MediatorLiveData<WeatherObject>()

        weatherRepo.getWeatherInfoAt("Amman")

        return liveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.dispose()
    }
}

