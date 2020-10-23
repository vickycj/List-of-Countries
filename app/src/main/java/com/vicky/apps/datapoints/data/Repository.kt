package com.vicky.apps.datapoints.data

import android.net.Uri
import com.vicky.apps.datapoints.base.NetworkConstant
import com.vicky.apps.datapoints.data.local.AppDataBase
import com.vicky.apps.datapoints.data.local.entities.CountryEntity
import com.vicky.apps.datapoints.data.remote.ApiService
import com.vicky.apps.datapoints.data.reponse.CountryDetailsResponse
import com.vicky.apps.datapoints.data.reponse.WeatherResponse
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val apiService: ApiService,
    private val appDataBase: AppDataBase
) {

    fun getDataFromApi(): Single<List<CountryDetailsResponse>> = apiService.getDataFromService()

    fun getCountryRowCount(): Single<Int> = appDataBase.countryDAO().getCountryRowCount()

    fun getCountryBasicInfo(): Flowable<List<CountryEntity>> = appDataBase.countryDAO().getAllCountries()

    fun getCountryDetailInfo(id: Int): Single<CountryEntity> = appDataBase.countryDAO().getCountryById(
        id
    )

    fun emptyCountryList() = appDataBase.countryDAO().emptyCountryList()

    fun insertDataToDB(data: List<CountryEntity>) = appDataBase.countryDAO().saveCountryList(data)

    fun getWeatherDataForLatLang(lat: String, lang: String) : Single<WeatherResponse> = apiService.getWeatherDataForLatLang(NetworkConstant.frameWeatherURL(lat, lang))



}