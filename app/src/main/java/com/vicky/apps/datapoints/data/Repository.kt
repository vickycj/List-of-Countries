package com.vicky.apps.datapoints.data

import com.vicky.apps.datapoints.data.remote.ApiService
import com.vicky.apps.datapoints.data.reponse.CountryDetailsResponse
import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val apiService: ApiService) {

    fun getDataFromApi(): Single<List<CountryDetailsResponse>> = apiService.getDataFromService()

}