package com.vicky.apps.datapoints.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.Repository
import com.vicky.apps.datapoints.data.local.entities.CountryEntity
import com.vicky.apps.datapoints.data.reponse.CountryDetailsResponse
import com.vicky.apps.datapoints.ui.model.CountryBasicInfo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


class MainViewModel(
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {


    private val countryDataSubscriber: MutableLiveData<Boolean> = MutableLiveData()

    fun getCountryDataSubscription(): MutableLiveData<Boolean> = countryDataSubscriber

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
                countryDataSubscriber.postValue(true)
            }, onError = {
                countryDataSubscriber.postValue(false)
            })
        )

    }

    private fun generateCall(): Flowable<List<CountryBasicInfo>> {
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

    fun checkCountOfCountriesAndCallApi() {
        compositeDisposable.add(frameRowCount().subscribeBy {
            if (it == 0) {
                callApiAndInsertIntoDB()
            } else {
                getCountryBasicInfo()
            }
        })
    }

    private fun callApiAndInsertIntoDB() {
        compositeDisposable.add(frameApi().subscribeBy(onSuccess = {
            formatAndInsertInDB(it);
        }, onError = {
            countryDataSubscriber.postValue(false)
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
        compositeDisposable.add(
            Observable.just(repository).
        subscribeOn(schedulerProvider.io).subscribe {
            repository.insertDataToDB(data)
                getCountryBasicInfo()
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