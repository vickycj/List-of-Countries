package com.vicky.apps.datapoints.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.Repository
import com.vicky.apps.datapoints.data.local.entities.CountryEntity
import com.vicky.apps.datapoints.data.reponse.CountryDetailsResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class SplashViewModel(
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    val response: MutableLiveData<Boolean> = MutableLiveData()

    private lateinit var compositeDisposable: CompositeDisposable

    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }

    fun checkCountOfCountriesAndCallApi() {
        compositeDisposable.add(frameRowCount().subscribeBy {
            if (it == 0) {
                callApiAndInsertIntoDB()
            } else {
                response.postValue(true)
            }
        })
    }

    private fun callApiAndInsertIntoDB() {
        compositeDisposable.add(frameApi().subscribeBy(onSuccess = {
           formatAndInsertInDB(it);
        }, onError = {
            it.message?.let { it1 -> response.postValue(false) }
        }))
    }

    private fun formatAndInsertInDB(list: List<CountryDetailsResponse>) {
        val data :MutableList<CountryEntity> = ArrayList()
        list.forEach {
            val country = CountryEntity()
            country.name = it.name.toString()
            country.capital = it.capital.toString()
            country.cioc = it.cioc.toString()
            country.nativeName = it.nativeName.toString()
            country.population = it.population!!
            country.subRegion = it.subregion.toString()
            country.flag = it.flag.toString()
            data.add(country)
        }

       saveData(data)
    }

    private fun saveData(data: MutableList<CountryEntity>) {
        compositeDisposable.add(Observable.just(repository).
        subscribeOn(schedulerProvider.io).subscribe {
            repository.insertDataToDB(data)
            response.postValue(true)
        })
    }


    private fun frameApi(): Single<List<CountryDetailsResponse>> {
        return repository.getDataFromApi()
            .compose(schedulerProvider.getSchedulersForSingle())
    }

    private fun frameRowCount(): Single<Int> {
        return repository.getCountryRowCount()
            .compose(schedulerProvider.getSchedulersForSingle())
    }
}