package com.vicky.apps.datapoints.base

import android.net.Uri

object NetworkConstant {

    val NETWORK_SCHEME = "https"

    val BASE_URL = "https://restcountries.eu"

    val WEATHER_URL_BASE = "api.openweathermap.org"

    val WEATHER_URL_PATH = "data"

    val WEATHER_VERSION = "2.5"

    val WAETHER_KEY = "weather"

    val API_KEY = "31e12155f86e0aa05424c6b254d663a7"

    fun frameWeatherURL (lat: String, lang: String) : String {
        val builder = Uri.Builder()
        builder.scheme(NETWORK_SCHEME)
            .authority(WEATHER_URL_BASE)
            .appendPath(WEATHER_URL_PATH)
            .appendPath(WEATHER_VERSION)
            .appendPath(WAETHER_KEY)
            .appendQueryParameter("lat", lat)
            .appendQueryParameter("lon", lang)
            .appendQueryParameter("appid", API_KEY)
            .appendQueryParameter("units", "metric")
        return builder.build().toString()
    }
}
