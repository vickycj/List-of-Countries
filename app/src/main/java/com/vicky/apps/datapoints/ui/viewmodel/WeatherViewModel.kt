package com.vicky.apps.datapoints.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.Repository
import com.vicky.apps.datapoints.data.reponse.WeatherResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class WeatherViewModel(
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val weatherDataSubscriber: MutableLiveData<WeatherResponse> = MutableLiveData()

    fun getWeatherDataSubscription(): MutableLiveData<WeatherResponse> = weatherDataSubscriber

    private lateinit var compositeDisposable: CompositeDisposable

    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }

    fun getWeatherData(lat: String, long: String) {
        compositeDisposable.add(frameApi(lat, long).subscribeBy(onSuccess = {
            weatherDataSubscriber.postValue(it)
        }, onError = {
            weatherDataSubscriber.postValue(null)
        }))
    }

    private fun frameApi(lat: String, long : String): Single<WeatherResponse> {
        return repository.getWeatherDataForLatLang(lat, long)
            .compose(schedulerProvider.getSchedulersForSingle())
    }

}