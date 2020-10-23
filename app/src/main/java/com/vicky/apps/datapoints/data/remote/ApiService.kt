package com.vicky.apps.datapoints.data.remote


import com.vicky.apps.datapoints.data.reponse.CountryDetailsResponse
import com.vicky.apps.datapoints.data.reponse.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {
    @GET("rest/v2/all")
    fun getDataFromService(): Single<List<CountryDetailsResponse>>

    @GET()
    fun getWeatherDataForLatLang(@Url url : String): Single<WeatherResponse>
}
