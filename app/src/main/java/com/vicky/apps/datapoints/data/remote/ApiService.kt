package com.vicky.apps.datapoints.data.remote


import com.vicky.apps.datapoints.data.reponse.CountryDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {
    @GET("rest/v2/all")
    fun getDataFromService(): Single<List<CountryDetailsResponse>>
}
