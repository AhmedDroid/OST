package com.ahmedroid.ost

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmedroid.data.State
import com.ahmedroid.data.models.WeatherDisplayData
import com.ahmedroid.data.repositories.WeatherRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


open class WeatherCardViewModel(
    app: App,
    private val weatherRepo: WeatherRepo
) : AndroidViewModel(app) {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getWeatherAt(city: String): LiveData<State<WeatherDisplayData>> {
        val liveData = MutableLiveData<State<WeatherDisplayData>>()

        compositeDisposable.add(weatherRepo.getWeatherInfoAt(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveData.value = State.Loading(true)
            }
            .subscribe({
                if (null != it) {
                    liveData.value = State.Success(it)
                }
                liveData.value = State.Loading(false)
            }, {
                liveData.value = State.Error(it.message)
                liveData.value = State.Loading(false)
            })
        )

        return liveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.dispose()
    }
}

