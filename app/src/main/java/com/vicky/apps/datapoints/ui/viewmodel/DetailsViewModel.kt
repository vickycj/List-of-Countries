package com.vicky.apps.datapoints.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.Repository
import com.vicky.apps.datapoints.data.local.entities.CountryEntity
import com.vicky.apps.datapoints.ui.model.KeyValue
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class DetailsViewModel  ( private val repository: Repository,
private val schedulerProvider: SchedulerProvider
) : ViewModel(){

    private val response: MutableLiveData<Boolean> = MutableLiveData()

    private val data: MutableList<KeyValue> = ArrayList()

    var title: String = "";
    var flag: String = "";

    fun getData() = data

    fun getSubscription(): MutableLiveData<Boolean> = response

    private lateinit var compositeDisposable: CompositeDisposable

    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }

    fun fetchSingleCountryDetails(id:Int) {
        compositeDisposable.add(frameDetails(id).subscribeBy {
            frameDataForUI(it)
            response.postValue(true)
        })
    }

    private fun frameDataForUI(it: CountryEntity) {
        data.clear()
        data.add(KeyValue("NAME",it.name))
        data.add(KeyValue("CAPITAL",it.capital))
        data.add(KeyValue("NATIVE NAME",it.nativeName))
        data.add(KeyValue("SUB REGION",it.subRegion))
        title = it.name
        flag = it.flag

    }

    private fun frameDetails(id: Int): Single<CountryEntity> {
        return repository.getCountryDetailInfo(id)
            .compose(schedulerProvider.getSchedulersForSingle())
    }
}