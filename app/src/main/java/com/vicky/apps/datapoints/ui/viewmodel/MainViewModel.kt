package com.vicky.apps.datapoints.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.Repository
import com.vicky.apps.datapoints.ui.model.CountryBasicInfo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


class MainViewModel(
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {


    private val response: MutableLiveData<Boolean> = MutableLiveData()

    fun getSubscription(): MutableLiveData<Boolean> = response

    private lateinit var compositeDisposable: CompositeDisposable

    private var mainData: List<CountryBasicInfo> = ArrayList()
    private var filteredData: List<CountryBasicInfo> = ArrayList()

    fun getCountryData() = mainData

    fun getFilteredCountryData() = filteredData

    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }


    fun getCountryBasicInfo() {
        compositeDisposable.add(
            generateCall().subscribeBy(onNext = {
                mainData = it
                response.postValue(true)
            }, onError = {
                response.postValue(false)
            })
        )

    }

    fun generateCall(): Flowable<List<CountryBasicInfo>> {
        return repository.getCountryBasicInfo()
            .compose(schedulerProvider.getSchedulersForFlowable())
    }

    fun search(query: String): Completable = Completable.create { it ->
        val list = mainData.filter {
            it.name.contains(query)
        }.toList()
        filteredData = list
        it.onComplete()
    }


}