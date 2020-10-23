package com.vicky.apps.datapoints.ui.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.AppConstants
import com.vicky.apps.datapoints.base.BaseFragment
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.data.reponse.WeatherResponse
import com.vicky.apps.datapoints.ui.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_weather.*
import java.util.*
import javax.inject.Inject


class WeatherFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var viewModel: WeatherViewModel

    override fun getLayout(): Int =  R.layout.fragment_weather


    override fun viewLoaded() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activityContext)
        initializeValues()
        intialiseLocationListener()

    }

    private fun isItNight() : Boolean {
        val isNight: Boolean
        val cal: Calendar = Calendar.getInstance()
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
        isNight = hour < 6 || hour > 18
        return isNight
    }

    private fun intialiseLocationListener() {
        if (checkSelfPermission(
                activityContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                getCurrentLocation(location)
            }
    }

    private fun initializeValues() {
        setTitle(getString(R.string.weather_info))
        viewModel = ViewModelProviders.of(this, factory).get(WeatherViewModel::class.java)
        viewModel.setCompositeData(compositeDisposable)

        viewModel.getWeatherDataSubscription().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                successCallback(it)
            } else {
                failureCallback()
            }
        })
    }

    private fun failureCallback() {
        Toast.makeText(activityContext, "DATA FETCH failed", Toast.LENGTH_LONG).show()
    }

    private fun successCallback(it: WeatherResponse) {
        val weatherId = it.weather?.get(0)?.let {
            it.id
        }
        val weatherDescription = it.weather?.get(0)?.let {
            it.description
        }
        setWeatherAnimation(weatherId)

      name.text = "Location : ${it.name}"
      pressure.text = "Weather : ${weatherDescription}"
      temperature.text = "Temperature : ${it.main?.temp}"
      feelsLike.text = "Feels Like : ${it.main?.feelsLike}"
    }

    private fun setWeatherAnimation(weatherId: Int?) {
        weatherId?. let {
            when(weatherId) {
                in 200..232 -> { updateWeatherCondition(AppConstants.THUNDERSTORM)}
                in 300..321 -> {updateWeatherCondition(AppConstants.DRIZZLE)}
                in 500..531 -> {updateWeatherCondition(AppConstants.RAIN)}
                in 600..622 -> {updateWeatherCondition(AppConstants.SNOW)}
                in 701..781 -> {updateWeatherCondition(AppConstants.ATMOSHPHERE)}
                800 -> {updateWeatherCondition(AppConstants.CLEAR)}
                in 801..804 -> {updateWeatherCondition(AppConstants.CLOUDS)}
            }
        }
    }

    private fun updateWeatherCondition(weather: String) {
        val animationElement = animationView
        updateBackGround()
        when (weather) {
            AppConstants.THUNDERSTORM -> {
              var id = 0
                id = if(isItNight()) {
                    R.raw.weather_storm_night
                } else {
                    R.raw.weather_storm
                }
                animationElement.setAnimation(id)
            }

            AppConstants.RAIN, AppConstants.DRIZZLE -> {
                var id = 0
                id = if(isItNight()) {
                    R.raw.weather_rainynight
                } else {
                    R.raw.weather_partly_shower
                }
                animationElement.setAnimation(id)
            }


            AppConstants.CLOUDS -> {
                var id = 0
                id = if(isItNight()) {
                    R.raw.weather_cloudynight
                } else {
                    R.raw.weather_partly_cloudy
                }
                animationElement.setAnimation(id)
            }

            AppConstants.CLEAR, AppConstants.ATMOSHPHERE -> {
                var id = 0
                id = if(isItNight()) {
                    R.raw.weather_night
                } else {
                    R.raw.weather_sunny
                }
                animationElement.setAnimation(id)
            }

            AppConstants.SNOW -> {
                var id = 0
                id = if(isItNight()) {
                    R.raw.weather_snownight
                } else {
                    R.raw.weather_snow_sunny
                }
                animationElement.setAnimation(id)
            }
        }
    }

    private fun updateBackGround() {
        if(isItNight()) {
           parent.setBackgroundResource(R.drawable.night_bg)
        }else {
            parent.setBackgroundResource(R.drawable.day_bg)
        }
    }


    private fun getCurrentLocation(location: Location?) {
        val lat = location?.latitude
        val long = location?.longitude

        lat?. let {
            long?. let {
                viewModel.getWeatherData(lat.toString(), long.toString())
            }
        }
    }
}